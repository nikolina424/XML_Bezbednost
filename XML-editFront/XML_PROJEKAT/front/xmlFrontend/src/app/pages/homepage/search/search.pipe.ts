import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {

  transform(results: any[], searchValue: string, selectedSearchType: string): any[] {
    if(!results || !searchValue){
      return results;
    }
    return results;//.filter(result => )
  }

}
