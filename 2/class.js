class IsbnValidation{
    constructor(digital){
        this.digital=digital
    }
    sayHi(){
        let a=this.digital+1
        return a

    }

}
let user=new IsbnValidation(5)
console.log(user.sayHi())
function listAllProperties(user){     
	var objectToInspect;     
	var result = [];
	
	for(objectToInspect = user; objectToInspect !== null; objectToInspect = Object.getPrototypeOf(objectToInspect)){  
		result = result.concat(Object.getOwnPropertyNames(objectToInspect));  
	}
	
	return result; 
}
function listAllProperties1(user){     
	var objectToInspect;     
	var result1 = [];
	
	for(objectToInspect = user; objectToInspect !== null; objectToInspect = Object.getPrototypeOf(objectToInspect)){  
		for(var key in objectToInspect){
            result1=result1.concat(objectToInspect[key])
        }  
	}
	
	return result1; 
}
let kek=listAllProperties(user)
let kek1=listAllProperties1(user)
console.log(kek)
console.log(kek1)

