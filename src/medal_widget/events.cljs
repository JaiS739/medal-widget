(ns medal-widget.events
  (:require
   [re-frame.core :as re-frame]
   [medal-widget.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced]]))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::sort-key
 (fn [db [_ val]]
   (assoc db :sort-key val)))

