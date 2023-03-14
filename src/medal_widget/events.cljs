(ns medal-widget.events
  (:require
   [re-frame.core :as re-frame]
   [medal-widget.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::sort-it
 (fn [db [_ item]]
   (let [current-data (:medals-data db)
         sorted (if (= item "gold")
                  (sort #(or (> (get %1 (keyword item)) (get %2 (keyword item)))
                             (and (= (get %1 (keyword item)) (get %2 (keyword item)))
                                  (> (get %1 :total) (get %2 :total)))) current-data)
                  (sort #(or (> (get %1 (keyword item)) (get %2 (keyword item)))
                             (and (= (get %1 (keyword item)) (get %2 (keyword item)))
                                  (> (get %1 :gold) (get %2 :gold)))) current-data))]
     (-> db
         (assoc :medals-data sorted)))))

