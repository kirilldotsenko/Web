
export default function listAllProperties(user){     
	var objectToInspect;     
	var result = [];
	
	for(objectToInspect = user; objectToInspect !== null; objectToInspect = Object.getPrototypeOf(objectToInspect)){  
		result = result.concat(Object.getOwnPropertyNames(objectToInspect));  
	}
	console.log(result);
	return result; 
}