import check from "./isbn"

describe("Check",function(){
    test("Simple test",function(){
        const res=check("978-3-16-148410-0")
        expect(res).toBe("ISBN-13")

    })
})

describe("Check",function(){
    test("Simple test 2",function(){
        const res=check("2-266-11156-6")
        expect(res).toBe("ISBN-10")

    })
})