import { Component,Input  } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Marque } from 'src/app/shared/models/marque';
import { MarqueService } from 'src/app/shared/services/marque.service';

@Component({
  selector: 'app-updatemarque',
  templateUrl: './updatemarque.component.html',
  styleUrls: ['./updatemarque.component.css']
})
export class UpdatemarqueComponent {
  @Input() idMarque: number;
  marque: Marque = new Marque();

  constructor(private marqueService: MarqueService, private route: ActivatedRoute) {
    this.marque = new Marque();
    this.route.params.subscribe(params => {
      this.idMarque = +params['id'];
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.idMarque = +params['id'];
      console.log('idMarque:', this.idMarque); 
      this.marqueService.getMarque(this.idMarque).subscribe(
        marque => {
          this.marque = marque;
        },
        error => {
          console.error('Error loading marque', error);
        }
      );
    });
  }

  updateMarque() {
    this.marqueService.updateMarque(this.idMarque, this.marque)
      .subscribe(() => console.log("Marque updated successfully"));
  }

}

 



