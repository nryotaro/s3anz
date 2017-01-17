(ns s3anz.core
  (:require [clojure.java.shell :as sh]
            [clojure.tools.logging :as log]
            ))

(defn deploy [config]
  (letfn [(output [logstr]
            (let [{ext :exit out :out err :err} logstr]
              (do (if (some? err) (log/error err))
                  (log/info out))))]
    (let [bucket (:s3bucket config)
          build (:build config)]
      (if (and (some? build) (some? bucket))
        (do
          (output (sh/sh "aws" "s3" "rm" (str "s3://" bucket)  "--recursive"))
          (output (sh/sh "aws" "s3" "sync" build (str "s3://" bucket))))))))
