(ns medal-widget.subs
  (:require
   [re-frame.core :as re-frame]))


(re-frame/reg-sub
 ::medals-data
 (fn [db [_ val]]
   (cond 
     (= val :gold) (sort-by (juxt (comp - val) (comp - (keyword "total"))) (:medals-data db))
     :else (sort-by (juxt (comp - val) (comp - (keyword "gold"))) (:medals-data db)))))


(re-frame/reg-sub
 ::sort-key
 (fn [db]
   (:sort-key db)))