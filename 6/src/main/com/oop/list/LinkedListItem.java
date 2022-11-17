package com.oop.list;

/**
 * Represents linked list node
 * @param <T> stored type
 */
public class LinkedListItem<T> {
  /**
   * Stored value
   */
  private T value;
  /**
   * Next node
   */
  private LinkedListItem<T> next;
  /**
   * Previous node
   */
  private LinkedListItem<T> previous;
  /**
   * List associated with this node
   */
  private LinkedList<T> list;

  /**
   * Creates new node instance for list
   * @param value stored value
   * @param list linked list which contains this node
   */
  LinkedListItem(T value, LinkedList<T> list) {
    this.value = value;
    this.list = list;
  }

  /**
   * Gets stored value
   * @return stored value
   */
  public T getValue() {
    return value;
  }

  /**
   * Sets stored value
   * @param value stored value
   */
  public void setValue(T value) {
    this.value = value;
  }

  /**
   * Gets next node
   * @return next node
   */
  public LinkedListItem<T> getNext() {
    return next;
  }

  /**
   * Gets previous node
   * @return previous node
   */
  public LinkedListItem<T> getPrevious() {
    return previous;
  }

  /**
   * Sets next node
   * @param next next node
   */
  void setNext(LinkedListItem<T> next) {
    this.next = next;
  }

  /**
   * Sets previous node
   * @param previous previous node
   */
  void setPrevious(LinkedListItem<T> previous) {
    this.previous = previous;
  }

  /**
   * Gets associated linked list
   * @return associated linked list
   */
  LinkedList<T> getList() {
    return list;
  }

  /**
   * Makes current node invalid
   */
  void invalidate() {
    next = null;
    previous = null;
    list = null;
  }
}
