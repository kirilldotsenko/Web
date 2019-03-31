import React from "react"

export default class Calculator extends React.Component
{
    constructor()
    {
        super();
        this.state={
            num1:0,
            num2:0,
            oper:'',
            result:0
        }
        this.FirstNumberChange=this.FirstNumberChange.bind(this);
        this.SecondNumberChange=this.SecondNumberChange.bind(this);
        this.OperChange=this.OperChange.bind(this);
        /*this.ResChange=this.ResChange.bind(this);*/
        this.Equal=this.Equal.bind(this);
    }
    FirstNumberChange(event){
        this.setState({num1:event.target.value});
        console.log(this.state.num1);
    }
    SecondNumberChange(event){
        this.setState({num2:event.target.value});
        console.log(this.state.num2);
    }
    OperChange(event){
        this.setState({oper:event.target.value});
        console.log(this.state.oper);
    }
    /*ResChange(event){
        this.setState({result:event.target.value});
        console.log(this.state.oper);
    }*/
    Equal(){
        if(this.state.oper=='+'){
            return this.state.result=Number(this.state.num1)+Number(this.state.num2),console.log(this.state.result);
        }
        else if(this.state.oper=='-'){
            return this.state.result=Number(this.state.num1)-Number(this.state.num2),console.log(this.state.result);
        }
        else if(this.state.oper=='/'){
            return this.state.result=Number(this.state.num1)/Number(this.state.num2),console.log(this.state.result);
        }
        else if(this.state.oper=='*'){
            return this.state.result=Number(this.state.num1)*Number(this.state.num2),console.log(this.state.result);
        }
    }
    render()
    {
        return <div>
            <input type="number" placeholder="Enter you 1st digit" value={this.state.num1} onChange={this.FirstNumberChange} />
            <select value={this.state.oper} onChange={this.OperChange}>
                    <option value='0'>Select</option>
                    <option value='+'>+</option>
                    <option value='-'>-</option>
                    <option value='*'>*</option>
                    <option value='/'>/</option>
            </select>
            <input type="number" placeholder="Enter you 2nd digit" value={this.state.num2} onChange={this.SecondNumberChange} />
            <button onClick={this.Equal}>=</button>  <label>{this.state.num1}{this.state.oper}{this.state.num2}{'='}{this.state.result}</label>
        </div>
    }
}