(ns medal-widget.views
  (:require
   [re-frame.core :as re-frame]
   [medal-widget.events :as events]
   [medal-widget.subs :as subs]
   ))

(defn table-head [sort-key]
  [:tr
   [:th "S.no"]
   [:th "Country"]
   [:th [:span.gold-btn {:class (cond (= sort-key :gold) "medal-selected") :on-click #(re-frame/dispatch [::events/sort-key :gold])}]]
   [:th [:span.silver-btn {:class (cond (= sort-key :silver) "medal-selected") :on-click #(re-frame/dispatch [::events/sort-key :silver])}]]
   [:th [:span.bronze-btn {:class (cond (= sort-key :bronze) "medal-selected") :on-click #(re-frame/dispatch [::events/sort-key :bronze])}]]
   [:th [:span.total-btn {:class (cond (= sort-key :total) "total-selected") :on-click #(re-frame/dispatch [::events/sort-key :total])} "Total"]]
   ])

(defn table-body [idx {:keys [code gold silver bronze total]}]
  [:tr {:key idx} 
   [:td (+ idx 1)]
   [:td code]
   [:td gold]
   [:td silver]
   [:td bronze]
   [:td total]])
 

(defn main-panel []
  (let [sort-key @(re-frame/subscribe [::subs/sort-key])
        medals-data @(re-frame/subscribe [::subs/medals-data sort-key])]
    [:div
     [:h1 "Medals Widget"]
     [:br]
     [:div
      [:table#medals
       [:thead
       (table-head sort-key)]
       [:tbody
        (map-indexed table-body medals-data)]]]]))
