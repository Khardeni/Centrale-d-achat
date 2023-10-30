import { Component } from '@angular/core';
import { MarqueService } from 'src/app/shared/services/marque.service';
import { faPlusCircle } from '@fortawesome/free-solid-svg-icons';
import { Marque } from 'src/app/shared/models/marque';


@Component({
  selector: 'app-marque',
  templateUrl: './marque.component.html',
  styleUrls: ['./marque.component.css']
})
export class MarqueComponent {
  listMarque:any=[];
  selectedMarque: Marque;
  
  
  plusIcon = faPlusCircle;
  marqueToDelete: Marque;
  

  constructor(private marqueService: MarqueService,) {   }

  ngOnInit(){
    this.marqueService.GetAllMarques().subscribe(Mlist => this.listMarque = Mlist);
  }
  deleteMarque(marque: Marque) {
    this.marqueToDelete = marque;
    this.marqueService.deleteMarque(marque.marqueId)
      .subscribe(() => {
        console.log('Marque deleted successfully');
        this.listMarque = this.listMarque.filter(m => m.marqueId !== marque.marqueId);
      }, error => {
        console.error('Error deleting Marque', error);
      });
  }
  selectMarque(marque: Marque) {
    this.selectedMarque = marque;
  }
  
    
}
    
  
  

