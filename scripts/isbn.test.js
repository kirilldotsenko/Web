import IsbnValidator from "./classISBN"
import listAllProperties from "./ClassProp"
import listAllProperties1 from "./ClassPropVal"
import ChangeIndex from "./Index"
import LevenDistance from "./Leven"

describe("Check",function(){
    test("Simple test 1",function(){
        const res=new IsbnValidator("2-266-11156-6")
        expect(res.Validate()).toBe("ISBN-10");

    })
})
describe("Check",function(){
    test("Simple test 2",function(){
        const res=new IsbnValidator("978-3-16-148410-0")
        expect(res.Validate()).toBe("ISBN-13");

    })
})
describe("Check",function(){
    test("Simple test 4",function(){
        var res=new IsbnValidator("978-3-16-148410-0")
        expect(listAllProperties(res)).toEqual(["value", "constructor", "Validate", "constructor", "__defineGetter__", "__defineSetter__", "hasOwnProperty", "__lookupGetter__", "__lookupSetter__", "isPrototypeOf", "propertyIsEnumerable", "toString", "valueOf", "__proto__", "toLocaleString"]);

    })
})
describe("Check",function(){
    test("Simple test 5",function(){
        var res=new IsbnValidator("978-3-16-148410-0")
        expect(listAllProperties1(res)).toEqual(["978-3-16-148410-0"]);

    })
})
describe("Check",function(){
    test("Simple test 6",function(){
        const res=ChangeIndex([1,2,3,4,5],0,3)
        expect(res).toEqual([2,3,4,1,5]);

    })
})
describe("Check",function(){
    test("Simple test 7",function(){
        const res=ChangeIndex([1,2,3,4,5],3,1)
        expect(res).toEqual([1,4,2,3,5]);

    })
})
describe("Check",function(){
    test("Simple test 8",function(){
        const res=LevenDistance("Hillo","Hello")
        expect(res).toBe(1);

    })
})