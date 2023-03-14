(ns medal-widget.views
  (:require
   [re-frame.core :as re-frame]
   [medal-widget.events :as events]
   [medal-widget.subs :as subs]
   ))

(def columns ["gold" "silver" "bronze" "Total"])

(defn table-body [idx {:keys [code gold silver bronze total]}]
  [:tr {:key code} 
   [:td (+ idx 1)]
   [:td code]
   [:td gold]
   [:td silver]
   [:td bronze]
   [:td total]])
 

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        medals-data @(re-frame/subscribe [::subs/medals-data])]
    [:div
     [:h1 "Medals Widget"]
     [:br]
     [:div
      [:table#medals
       [:thead
        [:tr
         [:th "S.no"]
         [:th "Country"]
         (map-indexed (fn [idx item]
                        (println idx)
                        [:th {:key item} [:button {:class (if (= item "gold")
                                                           "gold-button"
                                                           (if (= item "silver")
                                                             "silver-button"
                                                             (if (= item "bronze")
                                                               "bronze-button"
                                                               "total-button"))) :on-click #(re-frame/dispatch [::events/sort-it item])} (if (= item "Total") "Total" "")]]) columns)]]
       [:tbody
        (map-indexed (fn [idx item]
                       [table-body idx item]) medals-data)]]]]))
