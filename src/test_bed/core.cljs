(ns test-bed.core
  (:require [reagent.core :as r]))

(defn item-ui [item]
  [:div (pr-str item)])

(defn items [{:keys [items]}]
  [:div
   (for [item items]
     ^{:key (:id item)}
     [item-ui item])])

(defn app []
  (let [A '({:id 0, :text "hi"}
            {:id 1, :text "fry"})
        B '({:id 2, :text "bye"}
            {:id 3, :text "lie"}
            {:id 3, :text "lye"})
        state (r/atom 'A)]
    (fn []
      [:div
       [:div [:button {:on-click #(reset! state 'A)} "A"]
        [:button {:on-click #(reset! state 'B)} "B"]]
      (case @state
        A [items {:items A}]
        B [items {:items B}])])))

(r/render
 [app]
 (js/document.getElementById "app"))
