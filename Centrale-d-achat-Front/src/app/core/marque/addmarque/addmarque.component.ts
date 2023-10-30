import { Component } from '@angular/core';
import { Marque } from 'src/app/shared/models/marque';
import { MarqueService } from 'src/app/shared/services/marque.service';

@Component({
  selector: 'app-addmarque',
  templateUrl: './addmarque.component.html',
  styleUrls: ['./addmarque.component.css']
})
export class AddmarqueComponent {
  marque = {nomMarque : ''};

  constructor(private marqueService: MarqueService) { }

  addMarque() {
    this.marqueService.addMarque(this.marque)
      .subscribe(() => {
        // handle success
        console.log('Marque added successfully!');
        // reset the form
        this.marque = {nomMarque:''};
      }, (error) => {
        // handle error
        console.log('Error adding marque:', error);
      });
  }

}
