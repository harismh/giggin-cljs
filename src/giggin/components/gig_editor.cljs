(ns giggin.components.gig-editor
  (:require [giggin.helpers :refer [get-event-target
                                    get-event-checked]]))

(defn form-group-input
  [{:keys [id type value values]}]
  [:div.form__group
   [:label.form__label {:for id} id]
   [:input.form__input {:type type
                        :id id
                        :value value
                        :on-change #(swap! values assoc (keyword id) (get-event-target %))}]])

(defn form-group-checkbox
  [{:keys [id checked values]}]
  [:div.form__group
   [:label.form__label {:for id} id]
   [:label.form__switch
    [:input {:type :checkbox
             :checked checked
             :on-change #(swap! values assoc (keyword id) (get-event-checked %))}]
    [:i.form__icon]]])

(defn gig-editor
  [{:keys [modal values initial-values upsert-gig toggle-modal]}]
  (let [{:keys [title desc img price sold-out]} @values]
    [:div.modal (when (:active @modal) {:class "active"})
     [:div.modal__overlay]
     [:div.modal__container
      [:div.modal__body
       [form-group-input {:id "title" :type "text" :value title :values values}]
       [form-group-input {:id "desc" :type "text" :value desc :values values}]
       [form-group-input {:id "img" :type "text" :value img :values values}]
       [form-group-input {:id "price" :type "number" :value price :values values}]
       [form-group-checkbox {:id "sold-out" :checked sold-out :values values}]]
      [:div.modal__footer
       [:button.btn.btn--link.float--left
        {:on-click #(toggle-modal {:active false :gig initial-values})}
        "Cancel"]
       [:button.btn.btn--secondary
        {:on-click #(upsert-gig @values)}
        "Save"]]]]))