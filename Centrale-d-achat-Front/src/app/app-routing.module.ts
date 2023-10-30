import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FactureComponent } from './core/facture/facture.component';
import { PaiementComponent } from './core/paiement/paiement.component';
import { DeliveryComponent } from './core/delivery/delivery.component';
import { TrackDeliveryComponent } from './core/tracking/track-delivery/track-delivery.component';
import { CheckoutComponent } from './core/shop/checkout/checkout.component';
import { DetailComponent } from './core/facture/detail/detail.component';
import { SupportComponent } from './core/support/support.component';
import { TestComponent } from './core/test/test.component';
import { ShopComponent } from './core/shop/shop.component';
import { DashboardComponent } from './core/dashboard/dashboard.component';
import { HomeComponent } from './core/shop/home/home.component';
import { AboutComponent } from './core/shop/about/about.component';
import { ErrorComponent } from './shared/error/error.component';
import { HistoryComponent } from './core/shop/history/history.component';
import { HistoryDetailComponent } from './core/shop/history/history-detail/history-detail.component';
import { HealthComponent } from './core/health/health.component';
import { BackupsComponent } from './core/health/backups/backups.component';
import { DepartementComponent } from './core/departement/departement.component';
import { EmplacementComponent } from './core/emplacement/emplacement.component';
import { AddEmployeeComponent } from './core/employee/add-employee/add-employee.component';
import { AddEmplacementComponent } from './core/emplacement/add-emplacement/add-emplacement.component';
import { EditEmplacementComponent } from './core/emplacement/edit-emplacement/edit-emplacement.component';
import { EmplacementDetailComponent } from './core/emplacement/emplacement-detail/emplacement-detail.component';
import { DepartementDetailComponent } from './core/departement/departement-detail/departement-detail.component';
import { PerformanceComponent } from './core/employee/performance/performance.component';
import { EmpCallenderComponent } from './core/emp-callender/emp-callender.component';
import { ChargeComponent } from './core/charge/charge.component';
import { ImpotComponent } from './core/impot/impot.component';
import { CurrencyComponent } from './core/currency/currency.component';
import { PrComponent } from './core/shop/pr/pr.component';
import { AddProduitComponent } from './core/produit/add-produit/add-produit.component';
import { ProduitComponent } from './core/produit/produit.component';
import { AddmarqueComponent } from './core/marque/addmarque/addmarque.component';
import { AddCategorieComponent } from './core/categorie/add-categorie/add-categorie.component';
import { MarqueComponent } from './core/marque/marque.component';
import { UpdatemarqueComponent } from './core/marque/updatemarque/updatemarque.component';
import { UpdateCategorieComponent } from './core/categorie/update-categorie/update-categorie.component';
import { CategorieComponent } from './core/categorie/categorie.component';
import { EditproduitComponent } from './core/produit/editproduit/editproduit.component';
import { PanierComponent } from './core/shop/panier/panier.component';
import { BackComponent } from './core/back/back.component';
import { AuthGuard } from './shared/services/auth.guard';
import { AdminComponent } from './core/admin/admin.component';
import { LoginComponent } from './core/shop/login/login.component';
import { RegisterComponent } from './core/shop/register/register.component';
import { VerifyComponent } from './core/shop/login/verify/verify.component';
import { ResetComponent } from './core/shop/login/reset/reset.component';
import { ResetSMSComponent } from './core/shop/login/reset/reset-sms/reset-sms.component';
import { ResetMailComponent } from './core/shop/login/reset/reset-mail/reset-mail.component';

const routes: Routes = [

  {path: '', component: BackComponent,canActivate:[AuthGuard], data:{roles:['Admin']},
  children: [
    {path:'admin',component:AdminComponent},
    {path:"health",component:HealthComponent},
    {path:"health/backups",component:BackupsComponent},
    {path:"facture",component:FactureComponent},
    {path:"paiement",component:PaiementComponent},
    {path:"delivery",component:DeliveryComponent},
    {path:"paiement",component:PaiementComponent},
    {path:"tracking",component:TrackDeliveryComponent},
    {path:"checkout",component:CheckoutComponent},
    {path:"support/:text",component:SupportComponent},
    {path:"test",component:TestComponent},
    {path:"Empcalender",component:EmpCallenderComponent},
    {path:"charge",component:ChargeComponent},
    {path:"impot",component:ImpotComponent},
    {path:"currency",component:CurrencyComponent},
    {path:"shop",component:ShopComponent},
    {path:"departement",component:DepartementComponent},
    {path:"employee",component:EmplacementComponent},
    {path:"employee/performance/:idEmployee",component:PerformanceComponent},
    {path:"employee/de/:idEmpDep",component:DepartementDetailComponent},
    {path:"employee/add/:idEmp/:idDep",component:AddEmployeeComponent},
    {path:"emplacement",component:EmplacementComponent},
    {path:"emplacement/add",component:AddEmplacementComponent},
    {path:"emplacement/edit/:idEmp",component:EditEmplacementComponent},
    {path:"emplacement/dp/:idEmp",component:EmplacementDetailComponent},
    {path:"facture/detail/:factid",component:DetailComponent},
    {path:"dashboard",component:DashboardComponent},
    {path:"produit/addproduit", component: AddProduitComponent},
    {path:"produits",component:ProduitComponent},
    {path:"marques/add-marque", component: AddmarqueComponent},
    {path:"add-categorie", component: AddCategorieComponent},
    {path:"marques", component: MarqueComponent},
    {path:"marques/update-marque/:id", component: UpdatemarqueComponent},
    {path:"categories", component: CategorieComponent},
    {path:"categories/update-categorie/:id", component: UpdateCategorieComponent},
    {path:"produits/update/:id", component: EditproduitComponent},
    
  ]},
    {path:'login',component:LoginComponent},
    {path:'register',component:RegisterComponent},
    {path:"unauthorized",component:ErrorComponent},
    {path:"verify/:activateToken",component:VerifyComponent },
    {path:"reset",component:ResetComponent},
    {path:"resetSMS",component:ResetSMSComponent},
    {path:"resetMail",component:ResetMailComponent},
    {path:"shop",component: ShopComponent,
      children: [
        { path: '', redirectTo: 'home', pathMatch: 'full' },
        { path: 'home', component: HomeComponent },
        { path: 'about', component: AboutComponent },
        { path: 'history/:username', component: HistoryComponent },
        { path: 'history/detail/:idcommande', component: HistoryDetailComponent },
        { path: 'checkout', component:CheckoutComponent },
        { path: 'products', component: PrComponent },
        { path: 'panier', component: PanierComponent}
      ]},
    {path:"**",component:ErrorComponent}
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
