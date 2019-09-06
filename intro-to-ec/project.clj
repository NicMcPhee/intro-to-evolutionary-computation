(defproject intro-to-ec "0.1.0-SNAPSHOT"
            :description "FIXME: write description"
            :url "http://example.com/FIXME"
            :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
                      :url "https://www.eclipse.org/legal/epl-2.0/"}
            :dependencies [[org.clojure/clojure "1.10.0"]
                           [org.clojure/math.combinatorics "0.1.6"]
                           [proto-repl "0.3.1"]]
            :plugins [[jonase/eastwood "0.3.6"]
                      [lein-cljfmt "0.6.4"]
                      [lein-kibit "0.1.7"]]
            :aliases {"lint" ^{:doc "Execute cljfmt check, eastwood, and kibit"}
                      ["do" ["cljfmt" "check"]
                       ["eastwood" "{:source-paths [\"src\"]}"]
                       ["kibit"]]}
            :main ^:skip-aot intro-to-ec.core
            :target-path "target/%s"
            :profiles {:uberjar {:aot :all}
                       :dev {:dependencies [[org.clojure/test.check "0.9.0"]]}})
