package fastparse
object Compat{
  type Context = scala.reflect.macros.blackbox.Context
  def enclosingName(c: Context) = {
    c.internal.enclosingOwner
  }
}

trait UtilsCompat {
  import scala.collection.mutable

  /**
   * An trie node for quickly matching multiple strings which
   * share the same prefix, one char at a time.
   *
   * The `LongMap` could be pretty easily replaced by a lookup-table
   * `Array[TrieNode]`, but empirically that doesn't seem to give any
   * performance improvements.
   */
  final class TrieNode{
    private val _children = mutable.LongMap.empty[TrieNode]
    def children: mutable.Map[Long, TrieNode] = _children
    var word: String = null
    def apply(c: Char): TrieNode = {
      _children.getOrNull(c)
    }
  }
}