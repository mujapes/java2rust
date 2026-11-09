// Synthetic ownership regression matching original new ret(this,0) erased capture.
class CapturedOwner {
 int value;
 CaptureHolder capture() { return new CaptureHolder(this); }
}
class CaptureHolder {
 final Object captured;
 CaptureHolder(Object captured) { this.captured = captured; }
 CapturedOwner owner() { return (CapturedOwner) captured; }
}
class EscapingThisProbe {
 static boolean viaThis() { CapturedOwner owner = new CapturedOwner(); CaptureHolder saved = owner.capture(); owner.value = 7; return saved.owner() == owner && saved.owner().value == 7; }
 static boolean explicitOwner() { CapturedOwner owner = new CapturedOwner(); CaptureHolder saved = new CaptureHolder(owner); owner.value = 9; return saved.owner() == owner && saved.owner().value == 9; }
 public static void main(String[] args) { System.out.println(viaThis()); System.out.println(explicitOwner()); }
}
