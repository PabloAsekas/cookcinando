import { Component, OnInit } from '@angular/core';

// import { User } from './user.model';
// import { UsersService } from './users.service';

import { Recipe } from './recipe.model';
import { RecipesService } from './recipes.service';

@Component({
  // selector: 'app-root',
  templateUrl: './recipes.component.html'
  // styleUrls: ['../styles/app.component.css']
})

export class RecipesComponent implements OnInit {

    recipes: Recipe[] = [];
    private actualPage = 0;
    private nRecipes = 0;
    private loadMore = true;
    recEven: Recipe[] = [];
    recOdd: Recipe[] = [];
    recEvenEven: Recipe[] = [];
    recEvenOdd: Recipe[] = [];
    recOddOdd: Recipe[] = [];
    recOddEven: Recipe[] = [];
    
    
    constructor (private recipesService: RecipesService) { }

    ngOnInit() {
        this.recipesService.getRecipes().subscribe(
            recipes => {
                this.recipes = recipes.content;
                this.sortInColumns(this.recipes);
                console.log(this.recipes);
            },
            error => console.log(error)
        );
        this.recipesService.getAmountRecipes().subscribe(
            nRecipes => this.nRecipes = nRecipes,
            error => console.error(error)
        );
    }
    
    loadMoreRecipes(){
       this.actualPage += 1;
       this.recipesService.getRecipesPag('?page='+ this.actualPage +'&size=10').subscribe(
         recipes => {
             let newRecipes = Recipes[];
             if(this.recipes.length < this.nRecipes){
                 let moreRecipes = recipes;
                 this.sortInColumns(moreRecipes);
                 for(let recipe of moreRecipes){
                     this.recipes.push(recipe);
                 }

                 if(this.recipes.length == this.nRecipes){
                     this.loadMore = false;
                 }
             }
         },
         error => console.log(error)
       );

     }    

//    loadMoreRecipes(){
//       this.actualPage += 1;
//       this.recipesService.getRecipesPag('?page='+ this.actualPage +'&size=10').subscribe(
//         recipes => {
//           if(this.recipes.length < this.nRecipes){
//             let moreRecipes = recipes;
//             this.sortInColumns(moreRecipes);
//             for(let recipe of moreRecipes){
//                 this.recipes.push(recipe);
//             }
//
//             if(this.recipes.length == this.nRecipes){
//               this.loadMore = false;
//             }
//           }
//         },
//         error => console.log(error)
//       );
//
//     }

     sortInColumns(moreRecipes: Recipe[]){

        let added1 = true;
        let added2 = true;
        for(let r of moreRecipes){
          if(moreRecipes.indexOf(r) % 2 == 0){
            this.recEven.push(r);
          }else{
            this.recOdd.push(r);
          }
        }

        for(let recE of this.recEven){
            if(added1){
              this.recEvenEven.push(recE);
              added1 = false;
            }else{
              this.recEvenOdd.push(recE);
              added1 = true;
            }
        }

        for(let recO of this.recOdd){
            if(added2){
              this.recOddEven.push(recO);
              added2 = false;
            }else{
              this.recOddOdd.push(recO);
              added2 = true;
            }
        }

        this.recEven.splice(0,this.recEven.length);
        this.recOdd.splice(0,this.recEven.length);
        this.recEven.length = 0;
        this.recOdd.length = 0;
      }
}
