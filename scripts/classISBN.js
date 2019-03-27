import isbn from "./isbn"

export default class IsbnValidator {
    constructor(value){
        this.value=value;
    }
    Validate(){
        this.value;
        return isbn(this.value);
    }
};
/*export function listAllProperties(user){     
	var objectToInspect;     
	var result = [];
	
	for(objectToInspect = user; objectToInspect !== null; objectToInspect = Object.getPrototypeOf(objectToInspect)){  
		result = result.concat(Object.getOwnPropertyNames(objectToInspect));  
	}
	console.log(result);
	return result; 
}
export function listAllProperties1(user){     
	var objectToInspect;     
	var result1 = [];
	
	for(objectToInspect = user; objectToInspect !== null; objectToInspect = Object.getPrototypeOf(objectToInspect)){  
		for(var key in objectToInspect){
            result1=result1.concat(objectToInspect[key])
        }  
	}
	
	return result1; 
}*/