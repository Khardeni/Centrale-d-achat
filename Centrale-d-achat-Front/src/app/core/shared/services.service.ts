import { Injectable } from '@angular/core';
import { CurrencyService } from 'src/app/shared/services/currency.service';

@Injectable({
  providedIn: 'root'
})
export class ServicesService {

  constructor(private currencyService:CurrencyService) {
    
  }

}
