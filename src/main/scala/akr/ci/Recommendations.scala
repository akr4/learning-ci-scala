package akr.ci

/**
 * Warning: たぶん間違ってる
 * とりあえずコミットしたバージョン
 */
object Main extends Application {
    /**
     * 二人とも評価しているアイテムのリストを得る
     */
    private def commonMovies(reviews: List[Review], r1: Reviewer, r2: Reviewer): List[Movie] = {
        reviews.filter(_.reviewer == r1).map(_.movie).intersect(reviews.filter(_.reviewer == r2).map(_.movie))
    }

    def similarityDistance(reviews: List[Review], r1: Reviewer, r2: Reviewer): Double = {
        val bothReviewed = commonMovies(ReviewDb.reviews, r1, r2)

        // 両者ともに評価しているものが一つもなければ 0 を返す
        if (bothReviewed.size == 0) return 0.0

        // すべての差の平方を足し合わせる
        val reviewMap: Map[Movie, Tuple2[Review, Review]] = reviews.filter { r => bothReviewed.contains(r.movie) }
                .filter { r => r.reviewer == r1 || r.reviewer == r2 }
                .foldLeft(Map[Movie, Tuple2[Review, Review]]().withDefault(m => Tuple2(null, null))) {
                    (_, r) =>
                    r.reviewer match {
                        case r1 => r.movie -> (r,  _.apply(r.movie)._2)
                        case r2 => r.movie -> (_.apply(r.movie)._1,  r)
                    }
                }

        reviewMap.values.foldLeft(0.0) { (a, b) => a + Math.pow(b._1.rate.value - b._2.rate.value, 2) }
    }

    println(similarityDistance(ReviewDb.reviews, ReviewDb.lisa, ReviewDb.michael))
}
