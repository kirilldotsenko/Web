export default function listAllProperties1(user){     
	var objectToInspect;     
	var result1 = [];
	
	for(objectToInspect = user; objectToInspect !== null; objectToInspect = Object.getPrototypeOf(objectToInspect)){  
		for(var key in objectToInspect){
            result1=result1.concat(objectToInspect[key])
        }  
	}
	
	return result1; 
}