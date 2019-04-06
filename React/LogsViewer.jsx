import React from "react";

export default class LogsViewer extends React.Component
{
    render()
    {
        return this.props.logs.map((e,i) => {
            return <div key={i}>{JSON.stringify(e)}</div>
        })
    }
}