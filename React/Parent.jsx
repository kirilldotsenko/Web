import React from "react";
import Calculator from "./calculator";
import LogsViewer from "./LogsViewer.jsx";

export default class Parent extends React.Component
{
    constructor(props)
    {
        super(props);
        this.state = {
            logs: []
        }        

        this.onEqualsClick = this.onEqualsClick.bind(this);
    }

    onEqualsClick(obj)  {
        this.setState({
            logs: this.state.logs.concat(obj)
        })
        console.log(this.state.logs)
    }

    render()
    {
        return <div>
            <Calculator onEqualsClick={this.onEqualsClick} />
            <LogsViewer logs={this.state.logs}/>
        </div>
    }
}