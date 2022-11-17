package com.oop.list;

import java.util.StringJoiner;

/**
 * Represents circular double linked list
 * @param <T> stored value
 */
public class LinkedList<T> {
  /**
   * First node in linked list
   */
  private LinkedListItem<T> head;
  /**
   * Count of all nodes in linked list
   */
  private int count;

  /**
   * Gets first node in linked list
   * @return first node
   */
  public LinkedListItem<T> getFirst() {
    return head;
  }

  /**
   * Gets last node in linked list
   * @return last node
   */
  public LinkedListItem<T> getLast() {
    return head.getPrevious();
  }

  /**
   * Checks if linked list is empty
   * @return true if list is empty, otherwise false
   */
  public boolean isEmpty() {
    return head == null;
  }

  /**
   * Gets count of all nodes
   * @return count of all nodes
   */
  public int getCount() {
    return count;
  }

  /**
   * Adds new element as first node
   * @param item element
   * @return new node
   */
  public LinkedListItem<T> addFirst(T item) {
    LinkedListItem<T> newNode = new LinkedListItem<>(item, this);
    if (isEmpty()) {
      insertNodeEmptyList(newNode);
    } else {
      insertNodeBefore(head, newNode);
      head = newNode;
    }
    return newNode;
  }

  /**
   * Adds new element as last node
   * @param item element
   * @return new node
   */
  public LinkedListItem<T> addLast(T item) {
    LinkedListItem<T> newNode = new LinkedListItem<>(item, this);
    if (isEmpty()) {
      insertNodeEmptyList(newNode);
    } else {
      insertNodeBefore(head, newNode);
    }
    return newNode;
  }

  /**
   * Adds new element before node in that list
   * @param node node in that list
   * @param item element
   * @return new node
   */
  public LinkedListItem<T> addBefore(LinkedListItem<T> node, T item) {
    validateNode(node);
    LinkedListItem<T> newNode = new LinkedListItem<>(item, this);
    insertNodeBefore(node, newNode);
    if (node == head)
      head = newNode;
    return newNode;
  }

  /**
   * Adds new element after node in that list
   * @param node node in that list
   * @param item element
   * @return new node
   */
  public LinkedListItem<T> addAfter(LinkedListItem<T> node, T item) {
    validateNode(node);
    LinkedListItem<T> newNode = new LinkedListItem<>(item, this);
    insertNodeBefore(node.getNext(), newNode);
    return newNode;
  }

  /**
   * Removes node before provided node in that list
   * @param node node in that list
   */
  public void removeBefore(LinkedListItem<T> node) {
    validateNode(node);
    removeNode(node.getPrevious());
  }

  /**
   * Removes node after provided node in that list
   * @param node node in that list
   */
  public void removeAfter(LinkedListItem<T> node) {
    validateNode(node);
    removeNode(node.getNext());
  }

  /**
   * Tries to swap values of node's neighbours
   * @param node node in that list
   * @return true if successfully swapped, otherwise false
   */
  public boolean trySwapNeighbours(LinkedListItem<T> node) {
    validateNode(node);
    if (node == getFirst() || node == getLast())
      return false;
    T temp = node.getPrevious().getValue();
    node.getPrevious().setValue(node.getNext().getValue());
    node.getNext().setValue(temp);
    return true;
  }

  /**
   * Gets node in list by index
   * @param index node's index
   * @return found node
   */
  public LinkedListItem<T> getNode(int index) {
    if (index < 0 || index >= count)
      throw new IndexOutOfBoundsException(index);
    LinkedListItem<T> result = head;
    while (index > 0) {
      result = result.getNext();
      index--;
    }
    return result;
  }

  @Override
  public String toString() {
    if (isEmpty())
      return "[]";
    StringJoiner joiner = new StringJoiner(", ", "[", "]");
    joiner.add(head.getValue().toString());
    LinkedListItem<T> current = head.getNext();
    while (current != head) {
      joiner.add(current.getValue().toString());
      current = current.getNext();
    }
    return joiner.toString();
  }

  /**
   * Inserts node if list is empty
   * @param newNode node to insert
   */
  private void insertNodeEmptyList(LinkedListItem<T> newNode) {
    newNode.setNext(newNode);
    newNode.setPrevious(newNode);
    head = newNode;
    count++;
  }

  /**
   * Removes node from list
   * @param node node to remove
   */
  private void removeNode(LinkedListItem<T> node) {
    if (node == node.getNext()) {
      head = null;
    } else {
      node.getNext().setPrevious(node.getPrevious());
      node.getPrevious().setNext(node.getNext());
      if (head == node)
        head = node.getNext();
    }
    node.invalidate();
    count--;
  }

  /**
   * Inserts new node before node
   * @param node node in that list
   * @param newNode new node to insert
   */
  private void insertNodeBefore(LinkedListItem<T> node, LinkedListItem<T> newNode) {
    newNode.setNext(node);
    newNode.setPrevious(node.getPrevious());
    node.getPrevious().setNext(newNode);
    node.setPrevious(newNode);
    count++;
  }

  /**
   * Checks if provided node is valid for this list
   * @param node node to validate
   */
  private void validateNode(LinkedListItem<T> node) {
    if (node == null)
      throw new NullPointerException("Node parameter is null");
    if (node.getList() != this)
      throw new IllegalStateException("This node from another list or doesn't have any");
  }
}
