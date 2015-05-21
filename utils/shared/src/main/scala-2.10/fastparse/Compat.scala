package fastparse
object Compat{
  type Context = scala.reflect.macros.Context
  def enclosingName(c: Context) = {
    c.asInstanceOf[scala.reflect.macros.runtime.Context]
      .callsiteTyper
      .context
      .owner
      .asInstanceOf[c.Symbol]
  }
}

trait UtilsCompat {
  import scala.collection.mutable

  /**
   * An trie node for matching multiple strings which
   * share the same prefix, one char at a time.
   *
   * Using a HashMap instead of a LongMap in 2.10.
   * Someone cares about the perf of that? Anyone?
   */
  final class TrieNode{
    private val _children = mutable.HashMap.empty[Long, TrieNode]
    def children: mutable.Map[Long, TrieNode] = _children
    var word: String = null
    def apply(c: Char): TrieNode = {
      _children.getOrElse(c, null)
    }
  }
}