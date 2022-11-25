import Vue from "vue";

import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

Vue.component("font-awesome-icon", FontAwesomeIcon);

import { faLocationDot } from "@fortawesome/free-solid-svg-icons";
library.add(faLocationDot);

import { faLandmark } from "@fortawesome/free-solid-svg-icons";
library.add(faLandmark);

import { faCalendar } from "@fortawesome/free-solid-svg-icons";
library.add(faCalendar);

import { faPersonRunning } from "@fortawesome/free-solid-svg-icons";
library.add(faPersonRunning);

import { faBed } from "@fortawesome/free-solid-svg-icons";
library.add(faBed);

import { faCartShopping } from "@fortawesome/free-solid-svg-icons";
library.add(faCartShopping);

import { faUtensils } from "@fortawesome/free-solid-svg-icons";
library.add(faUtensils);
