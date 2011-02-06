package akr.ci

class Recommendations(db: ReviewDb) {

    private def reviewsBy(r: Reviewer) = {
        db.findAllReviews().filter { _.reviewer == r }
    }

    /**
     * 二人とも評価している Movie のリストを返す
     */
    private[ci] def reviewedMovies(r1: Reviewer, r2: Reviewer): List[Movie] = {
        db.findAllReviews().filter(_.reviewer == r1).map(_.movie).intersect(db.findAllReviews().filter(_.reviewer == r2).map(_.movie))
    }

    /**
     * Reviewer の距離を計算する
     */
    def similarityDistance(r1: Reviewer, r2: Reviewer): Double = {
        val bothReviewed = reviewedMovies(r1, r2)

        // 両者ともに評価しているものが一つもなければ 0 を返す
        if (bothReviewed.isEmpty) return 0.0

        // すべての差の平方を足し合わせる
        val reviewMap: Map[Movie, Tuple2[Review, Review]] = db.findAllReviews().filter { r => bothReviewed.contains(r.movie) }
                .filter { r => r.reviewer == r1 || r.reviewer == r2 }
                .foldLeft(Map[Movie, Tuple2[Review, Review]]().withDefault(m => Tuple2(null, null))) {
                    (a, b) => a
/*
                    r.reviewer match {
                        case r1 => r.movie -> (r,  _.apply(r.movie)._2)
                        case r2 => r.movie -> (_.apply(r.movie)._1,  r)
                    }
*/
                }

        reviewMap.values.foldLeft(0.0) { (a, b) => a + Math.pow(b._1.rate.value - b._2.rate.value, 2) }
    }
}

object Main extends Application {
    println(new Recommendations(InMemoryReviewDb).similarityDistance(InMemoryReviewDb.lisa, InMemoryReviewDb.michael))
}
