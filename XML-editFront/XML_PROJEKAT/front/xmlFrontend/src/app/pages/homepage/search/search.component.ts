import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  //valedateForm: FormGroup = new FormGroup();
  searchValue: string = "";
  listOfData: any[]=[];

  constructor() { }

  ngOnInit(): void {
  }

}
