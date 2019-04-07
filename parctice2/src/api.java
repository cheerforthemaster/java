public interface api<T extends Comparable<T>> {
    public boolean insert(T Data);
    public boolean visit();
    public void search(T Data);
}
