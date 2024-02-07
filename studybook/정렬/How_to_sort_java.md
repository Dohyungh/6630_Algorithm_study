# 객체의 정렬


정렬은 기본적으로 **두개씩** 비교하는 것임.  
두개를 인자로 받아서 함수를 돌렸을 때,  
그 결과가  
**'음수'면 자리를 바꾸고,**  
'0 or 양수'면 자리를 안바꾸는 메커니즘으로 돌아감.

일단 오름차순 이면 o1 - o2 를,
내림차순 이면 o2 - o1 을 리턴하는 것이 맞음.

> ____ 은 내가 만든 클래스이름을 의미함

## Collections.sort()


1. 객체 클래스 에 Comparable<>을 implement 한다.


```java
Cat implements Comparable<Cat> {

    @Override
    public int compareTo(Cat c) {
        //2순위 기준
        if(원하는 필드의 동등조건){
            return this.a - c.a;
        }

        //1순위 기준
        return this.name.compareTo(o.name); // string은 자체 compareTo가 작성되어 있음

    }
}
```

2. Comparator 클래스를 새롭게 정의하고, 그 생성자로 생성한 객체를 Collections.sort에 제시한다. (2줄을 써야함.)
```java
public class ___Comparator implements Comparator<___>{

    @Override
    public int compare(___ o1, ___o2){// 앞에 있는 파라미터가 앞에 있음을 의미함.
        if (원하는 필드의 동등조건){
            return o1.필드 - o2.필드 // 무조건 <0 을 생각하고 뭐가 뭐보다 크게 하고 싶은지 항을 넘겨봐
        }
        return o1.name.compareTo(o2.name);
    }
}
```


```java
Collections.sort(List_변수명, new ___Comparator());
```

**! 결론적으로 3번에 익숙해지면 됨**  
3. 람다 표현식과 익명 클래스 (2번에서 2줄 쓰던 것을 1번에 쓰는 것임.)
```java
Collections.sort(List_변수명, (Cat c1, Cat c2)->{
    if(원하는 필드의 동등조건){
        return o1.필드 - o2.필드
    }
    return o1.name.compareTo(o2.name);

});
```

# 배열의 정렬

**! 3번은 Arrays.sort 에도 똑같이 적용 가능**
## Arrays.sort()
```java
Arrays.sort(리스트명, (o1, o2)-> {
    if(o1[0]==o2[0]){
        return o1[1] - o2[1];
    
    } else {return (o1[0]-o2[0]);}


});
```