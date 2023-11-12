# Molang

Molang is a scripting language in which a developer can interact with minecraft.
Monstera has an integrated DSL to help the developer write correct and predictable Queries, Variables and Math
operations.

````kotlin
animationController("state") {
    animStates {
        animState("default") {
            transitions {
                transition("state") { 
                    Query.markVariant eq Math.random(1, 10) 
                    or
                    !Query.isBaby
                } 
            }
        }
    }
}
````

We now want to discuss in detail how this works:

## Queries

Monstera will provide all Queries in the class `Query`. To access them type

````kotlin
Query.variant
````

This will return a `Query` object with the `Molang` interface. That means the
`Query` object on its own is valid for any Molang input like in the animation controller transition:

````kotlin
animationController("state") {
    animStates {
        animState("default") {
            transitions { 
                //                    <  Molang input  >
                transition("state") { Query.isOnGround } 
            }
        }
    }
}
````

## Math

Note: Make sure to import the correct object (`import com.lop.devtools.monstera.addon.molang.Math`)

Monstera will also provide all Math operations for Molang. Access them on the `Math` object:

````kotlin
Math.abs(-2)
````

This will also return a `Math` object which has again the Molang interface, so it can also
be used on its own in a Molang input field.

Basic math is also supported within a Molang context:

````kotlin
(Query.variant + 1) lt Math.abs(-2)
````

Possible operations are

- "+", "-", "*", "/", "!"

while the "!" is a negation for a bool like

````kotlin
!Query.isBaby
````

is equal to 

````kotlin
Query.isBaby eq false
````

## Variables

To define Variables, access the `Variable` object and call

````kotlin
Variable.new("name_of_the_var", Query.variant)
````

to use the variables in a Query:

````kotlin
Variable.get("name_of_the_var")
//or
Variable["name_of_the_var"] 
````

## Connecting Molang expressions

- Monstera will read and interpret Molang expressions from left to right unless defined otherwise

````kotlin
Query.variant eq 0 or Query.variant eq 1 and Query.variant eq 2
//is the same as
(Query.variant eq 0 or Query.variant eq 1) and Query.variant eq 2
````

- Connect Molang expressions with operators like `and`, `or`, ...

all possibilities:

| KeyWord       | Result |
|---------------|--------|
| and           | &&     |
| or            | ll     |
| eq            | ==     |
| equals        | ==     |
| neq           | !=     |
| notEquals     | !=     |
| sm            | <      |
| smaller       | <      |
| less          | <      |
| lt            | <      |
| seq           | <=     |
| smallerEquals | <=     |
| lessEquals    | <=     |
| bg            | &gt;   |
| gt            | &gt;   |
| bigger        | &gt;   |
| beq           | &gt;=  |
| biggerEquals  | &gt;=  |
| greaterEquals | &gt;=  |