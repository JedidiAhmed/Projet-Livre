import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'searchFilter'
})
export class SearchFilterPipe implements PipeTransform {
  transform(value: any, ...args: any[]) {
    throw new Error('Method not implemented.');
  }

  /*transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }*/

  ransform(list: any[], filterText: string): any {
    return list ? list.filter(item =>
    item.titreLivre.toLowerCase().includes(filterText)) : [];
    }
}
