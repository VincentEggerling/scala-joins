# scala-joins (WIP)
Mini library to perform join operations between Scala sequences in a fully immutable way.

There are two main objects that provide functions to perform joins: `InnerJoin` and `LeftJoin`. By convention,
we call `A` the type of the left sequence, and `B` the type of the right sequence.

## The functions:
- LeftJoin:
    - The functions return a `Seq[A, Seq[B]]`.
    - `toCartesian` *flattens* the result into a `Seq[(A, Option[B])]`

- InnerJoin:
    - The functions return a `Seq[A, NonEmptySeq[B]]`.
    - `toCartesian` *flattens* the result into a `Seq[(A, B)]`

For each join type, we provide all combinations of the following implementations:
- Linear/Binary:
    - Linear implementations go over both sequences to check for key match in a *two-nested-for-loops* style.
    This usually results in an `O(A*B)` time.
    - Binary implementations go over the left sequence linearly and for each left key, perform a binary search
    on the right sequence to check for a match. The right sequence does not need to be sorted by the caller. It
    must however be an `IndexedSeq`.
    This usually results in an `O((A+B)log(B))` time.
    
- Direct A-B comparison/Reduced to primary key:
    - Direct A,B comparison: The caller needs to provide a comparison trait to compare the elements of the left
    sequence with the elements of the right sequence, such as `Equalable[A,B]`.
    - Reduced to primary key: The caller needs to provide a way to reduce the elements of the left and right sequence
    to a common primary key `C` that can be compared using a comparison trait such as `Equalable[C,C]`. This
    can lead to better performance that Direct A-B comparison if `Equalable[C,C]` is cheaper than `Equalable[A,B]`.
     
     
## The comparison traits
This library defines two comparisons trait: `Equalable[A,B]` and `LessThanable[A,B]`. Each requires exactly
one comparison function to compare `A` and `B`. The join functions may require implicitly one or more of
those traits to perform the join. We also provide implicit instances of these traits for types that already
implement `Ordering` such as `Int`.

## Build and run tests
To run the tests, you need SBT. 
- `cd ~/myGits`
- `git clone https://github.com/VincentEggerling/scala-joins.git`
- `cd scala-joins`
- `sbt test`