package akr.ci

import org.specs._

object RecommendationsSpec extends Specification {

    "二人とも評価している Movie のリストを得る" in {
        val reviews = List(
            Review(Reviewer("r1"), Movie("m1"), Rate(1.0)),
            Review(Reviewer("r1"), Movie("m2"), Rate(1.0)),
            Review(Reviewer("r2"), Movie("m1"), Rate(1.0)))
        Recommendations.commonMovies(reviews, Reviewer("r1"), Reviewer("r2")) must beEqual(List(Movie("m1")))
    }

    "共通の Movie がない場合は空のリストが返る" in {
        val reviews = List(
            Review(Reviewer("r1"), Movie("m1"), Rate(1.0)),
            Review(Reviewer("r1"), Movie("m2"), Rate(1.0)),
            Review(Reviewer("r2"), Movie("m3"), Rate(1.0)))
        Recommendations.commonMovies(reviews, Reviewer("r1"), Reviewer("r2")) must beEqual(List())
    }

}
