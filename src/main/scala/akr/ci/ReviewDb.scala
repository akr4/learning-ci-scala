package akr.ci

case class Reviewer(name: String)

case class Movie(title: String)

case class Rate(value: Double)

case class Review(reviewer: Reviewer, movie: Movie, rate: Rate)

object ReviewDb {
    implicit def toRate(value: Double): Rate = Rate(value)

    val lisa = Reviewer("Lisa Rose")
    val gene = Reviewer("Gene Seymour")
    val michael = Reviewer("Michael Phillips")
    val claudia = Reviewer("Claudia Puig")
    val mick = Reviewer("Mick LaSalle")
    val jack = Reviewer("Jack Matthews")
    val toby = Reviewer("Toby")

    val ladyInTheWater = Movie("Lady in the Water")
    val snakesOnAPlane = Movie("Snakes on a Plane")
    val justMyLuck = Movie("Just My Luck")
    val supermanReturns = Movie("Superman Returns")
    val youMeAndDupree = Movie("You, Me and Dupree")
    val theNightListener = Movie("The Night Listener")

    val reviews = List(
        Review(lisa, ladyInTheWater, 2.5),
        Review(lisa, snakesOnAPlane, 3.5),
        Review(lisa, justMyLuck, 3.0),
        Review(lisa, supermanReturns, 3.5),
        Review(lisa, youMeAndDupree, 2.5),
        Review(gene, ladyInTheWater, 3.0),
        Review(gene, snakesOnAPlane, 3.5),
        Review(gene, justMyLuck, 3.5),
        Review(gene, supermanReturns, 5.0),
        Review(gene, theNightListener, 3.0),
        Review(gene, youMeAndDupree, 3.5),
        Review(michael, ladyInTheWater, 2.5),
        Review(michael, snakesOnAPlane, 3.0),
        Review(michael, supermanReturns, 3.5),
        Review(michael, theNightListener, 4.0),
        Review(claudia, snakesOnAPlane, 3.5),
        Review(claudia, justMyLuck, 3.0),
        Review(claudia, theNightListener, 4.5),
        Review(claudia, supermanReturns, 4.0),
        Review(claudia, youMeAndDupree, 2.5),
        Review(mick, ladyInTheWater, 3.0),
        Review(mick, snakesOnAPlane, 4.0),
        Review(mick, justMyLuck, 2.0),
        Review(mick, supermanReturns, 3.0),
        Review(mick, theNightListener, 3.0),
        Review(mick, youMeAndDupree, 2.0),
        Review(jack, ladyInTheWater, 3.0),
        Review(jack, snakesOnAPlane, 4.0),
        Review(jack, theNightListener, 3.0),
        Review(jack, supermanReturns, 5.0),
        Review(jack, youMeAndDupree, 3.5),
        Review(toby, snakesOnAPlane, 4.5),
        Review(toby, youMeAndDupree, 1.0),
        Review(toby, supermanReturns, 4.0)
    )
}