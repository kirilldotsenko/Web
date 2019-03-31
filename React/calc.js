import React from "react";
import ReactDOM from "react-dom";
import Calculator from "./calculator";

const div = document.createElement("div");

window.onload = function()
{
    document.body.appendChild(div);
    ReactDOM.render(<Calculator  />, div);
}
