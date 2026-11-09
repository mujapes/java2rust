class ThrowableAllocationIdentity {
 static boolean alias() { Exception value = new Exception("same"); Exception alias = value; return value == alias; }
 static boolean distinct() { Exception first = new Exception("same"); Exception second = new Exception("same"); return first != second; }
 static boolean sharedSuppressed() { Exception value = new Exception("root"); Exception alias = value; Exception child = new Exception("child"); alias.addSuppressed(child); return value.getSuppressed().length == 1 && value.getSuppressed()[0] == child; }
 static boolean separateSuppressed() { Exception first = new Exception("same"); Exception second = new Exception("same"); first.addSuppressed(new Exception("child")); return second.getSuppressed().length == 0; }
}
