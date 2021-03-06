﻿using System;
using Is10;
using Is13;

namespace ISBN
{
    class IsbnValidator
    {
        public static int Main(string[] args)
        {
            if(args.Length!=0)
                Console.Write("Hello");
            string isbn = args[0];
            string[] arr = args[0].Split('-');
            if (arr[3] == "X")
                arr[3] = "10";
            isbn = isbn.Replace("-", "");
            int id = Int32.Parse(arr[3]);
            int lenStr = isbn.Length;
            if (lenStr == 10)
            {
                Isbn10 obj = new Isbn10();
                obj.Isb10(args[0]);
            }
            else
            {
                Isbn13 obj = new Isbn13();
                obj.Isb13(args[0]);
            }
            return 1;
        }
    }
}

/*public class Isbn10
{
    public int Isb10(string numb)
    {
        string isbn10 = numb;
        string[] arr = isbn10.Split("-");
        if (arr[3] == "X")
            arr[3] = "10";
        isbn10 = isbn10.Replace("-", "");
        int id = Int32.Parse(arr[3]);
        int lenArr = arr.Length;
        int lenStr = isbn10.Length;
        if ((lenStr == 10) && (lenArr == 4) && (id < 10))
        {
            double sum = 0;
            double newnumb = Int32.Parse(isbn10.Substring(0, 9));
            int fact = 2;
            while (newnumb > 0)
            {
                sum += (newnumb % 10) * fact;
                newnumb = Math.Floor(newnumb / 10);
                fact++;
            }
            if ((sum + id) % 11 == 0)
            {
                Console.Write("ISBN-10");
            }
        }
        Console.ReadKey();
        return 1;
    }
}
public class Isbn13
{
    public int Isb13(string numb)
    {
        string isbn10 = numb;
        string[] arr = isbn10.Split("-");
        if (arr[3] == "X")
            arr[3] = "10";
        isbn10 = isbn10.Replace("-", "");
        int id = Int32.Parse(arr[3]);
        int lenArr = arr.Length;
        int lenStr = isbn10.Length;
        if ((lenStr == 10) && (lenArr == 4) && (id < 10))
        {
            double sum = 0;
            double newnumb = Int32.Parse(isbn10.Substring(0, 12));
            int count = 2;
            while (newnumb > 0)
            {
                if (count % 2 == 0)
                {
                    sum += (newnumb % 10) * 3;
                    Math.Floor(newnumb / 10);
                    count++;
                }
                else
                {
                    sum += (newnumb % 10) * 1;
                    Math.Floor(newnumb / 10);
                    count++;
                }
                if (sum % 10 == 0)
                {
                    Console.Write("ISBN-13");
                }
            }
        }
        Console.ReadKey();
        return 1;
    }
}
}*/
