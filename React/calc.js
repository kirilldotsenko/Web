import React from "react";
import ReactDOM from "react-dom";
import Parent from "./Parent";

const div = document.createElement("div");

window.onload = function()
{
    document.body.appendChild(div);
    ReactDOM.render(<Parent  />, div);
}
