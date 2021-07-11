
/*
TODO:
- Think about several same elements match in the right seq. Like if you give an Equalable[A,B] = true.
  Might lead to really bad performance. Need to think how to compute big-O running time in degenerate cases.
- Think about when A =:= B, can we optimize ?

- Actually reduction functions could be done by the caller. They simply need to encapsulate A and B in a tuple
  with C, like (A,C) and (B,C) and then give a proper Equalable[(A,C),(B,C)]. Unless we can optimize thing on our side.
- Inner joins can be implemented using Left joins. Probably less performant though.
 */

object Main {

  def main(args: Array[String]) = {
    println("Hello scala-joiner !")
  }

}
