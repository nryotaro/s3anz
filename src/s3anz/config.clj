(ns s3anz.config)

(def config {:hooks {:deploy [(symbol "s3anz.core" "deploy")]}})
