import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { AdminComponent } from './admin/admin.component';
import { CustomerComponent } from './customer/customer.component'; // adjust path if needed
import { CdashboardComponent } from './customer/components/cdashboard/cdashboard.component';
import { DashboardComponent } from './admin/components/dashboard/dashboard.component';
import { PostCategoryComponent } from './admin/components/post-category/post-category.component';
import { PostProductComponent } from './admin/components/post-product/post-product.component';
import { CartComponent } from './customer/components/cart/cart.component';
import { PlaceOrderComponent } from './customer/components/place-order/place-order.component';
import { OrdersComponent } from './admin/components/orders/orders.component';
import { MyOrdersComponent } from './customer/components/my-orders/my-orders.component';
import { PostProductFaqComponent } from './admin/components/post-product-faq/post-product-faq.component';
import { UpdateProductComponent } from './admin/components/update-product/update-product.component';
import { ViewOrderedComponentsComponent } from './customer/components/view-ordered-components/view-ordered-components.component';
import { ReviewOrderProductComponent } from './customer/components/review-order-product/review-order-product.component';
import { ViewProductDetailComponent } from './customer/components/view-product-detail/view-product-detail.component';
import { WishlistComponent } from './customer/components/wishlist/wishlist.component';
import { TrackorderComponent } from './trackorder/trackorder.component';
import { AnalyticsComponent } from './admin/components/analytics/analytics.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: SignupComponent },
  {
    path: 'admin',
    component: AdminComponent,
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', component: DashboardComponent },
      { path: 'category', component: PostCategoryComponent },
      { path : 'products', component : PostProductComponent},
      { path : 'product/:productId', component : UpdateProductComponent},
      { path : 'orders', component : OrdersComponent},
      { path : 'analytics', component : AnalyticsComponent},
      { path : 'faq/:productId', component : PostProductFaqComponent}
    ]
  },

  {
    path: 'customer',
    component: CustomerComponent,
    children: [
      { path: '', redirectTo: 'cdashboard', pathMatch: 'full' },
      { path: 'dashboard', component: CdashboardComponent },
      { path : 'cart', component : CartComponent},
      { path : 'order' , component : PlaceOrderComponent},
      { path : 'orders' , component : MyOrdersComponent},
      { path : 'orderedProducts/:orderId', component : ViewOrderedComponentsComponent},
      { path : 'review/:productId', component : ReviewOrderProductComponent},
      { path : 'product/:productId', component : ViewProductDetailComponent},
      { path : 'wishlist', component : WishlistComponent}
    ]
  },
  { path : 'order' , component : TrackorderComponent}


];
