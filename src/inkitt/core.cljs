(ns inkitt.core
  (:require [reagent.core :as reagent :refer [atom]]
            [clojure.string :refer [split-lines trim]]))

(def text "I learned to code when I was a kid.
  I learned in C++, because that was the language that a game called Wolfenstein 3d was built in.

  I was obsessed with that game, in all its blocky, heavily pixelated beauty. Modding it was my hobby, and I used to build new versions of the game all the time, under the username Raistlin.

  Really, I learned to code because it was an extension of who I was; a kid who couldn’t just leave anything alone, who had to remake and rebuild and design and craft new things.

  I was like that in the real world — my hands were always busy — and it felt natural to adapt that to a computer.

  I had no idea what I was doing, when I first downloaded the source code of the game and started hacking around in it, and seeing what happened when I recompiled. But I can remember the moment it clicked.

  I worked out how to make the entire game slow down at the press of a button, and I called it a primitive version of every action movie’s slow motion effect.

  …and I thought I was a veritable God. I felt like I’d cracked open a secret and had an enormous new power. I couldn’t even sleep that night, because I wanted to keep building. I wanted to keep throwing together pieces of (fuck-ugly) code.

  I learned to code because it was a new way to make things. And making things was the only part of my life that I had any interest with.

  I’ve been talking to a lot of people lately, about how to get kids interested in coding. I know there’s a lot of stuff out there, and a lot of people are working on the idea. I think it’s one of the biggest ways you can impact the future right now.

  To me, code is as natural to a kid as playing, as making and building. Because that’s what kids do, they explore and experiment and they craft. It’s a pretty natural behavior. You give a kid a set of Lego blocks and they know what to do with it.

  That’s why I’ve already started buying play sets and toys for my niece that can introduce her to the world of code. I’m not going to pressure her into this stuff, and I don’t honestly think I’d ever have to.

  Because there’s enough there that kids already love, enough to play with and explore and break and fix and solve.

  I think at this point, software and oxygen have a lot in common. They’re both vital, they’re both everywhere, and we take them for granted. The way all this code affects our lives is becoming more and more profound, if you stop and examine it.

  But for a universal skill, for something that has a pretty low barrier to entry and a huge payoff, it’s still quite shocking to me how few people are exposed to it, or encouraged to embrace it.

  I’d call code the great equalizer, if its distribution as a skill set weren’t so unequal.

  I want to encourage the kids in my life, my niece and my friends’ kids, to start playing with code. Because I want to see a world where more people have the skills to change and remodel what doesn’t work.

  There’s an old saying, “Time is a game played beautifully by children.” I’ve always loved that. Kids have a different way of looking at the world, without the pressures and the panic and the desperation that we get as adults.")

(defn not-empty? [line]
  (not (empty?
        (trim line))))

(defn text-to-paragraphs [text]
  (let [paragraphs (split-lines text)]
    (map #(assoc {} :text % :comments [])
         (filter not-empty? paragraphs))))

(def article
  (atom
   {:paragraphs (text-to-paragraphs text)}))

;; -------------------------
;; Views

(defn post []
  (let [paragraphs (:paragraphs @article)]
    [:div
     (for [p paragraphs]
       [:div.inkitt-paragraph
        [:p (:text p)]
        [:div.comment (count (:comment p))]]
       
       )]))
(defn home-page []
  [:div
   [:h2 "Inkitt test"]
   [post]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))