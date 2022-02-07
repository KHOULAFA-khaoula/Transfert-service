<template>
  <div class="row">
    <div class="col-12">
      <card  title="Console">
        <!-- <vue-ellipse-progress align-right :progress="progress" fontSize="2rem">{{progress}}%</vue-ellipse-progress> -->
        <!-- <k-progress :percent="progress" /> -->
        <div v-if="KeyO">
        <button v-on:click="envoyer()" style="margin-left:350px"  class="step-button step-button-previous">ENVOYER</button>
        <button v-on:click="receive()"  class="step-button step-button-previous">RECEVOIR</button>
        </div>
        <form-wizard @onComplete="onComplete" style="background-color: #27293d;" v-if="key1">
          <tab-content title="About Sender" :selected="true">
            <div v-if="ChoixClient">
            <button v-on:click="newC()" style="margin-left:250px"  class="step-button step-button-previous">New Client</button>
            <button v-on:click="oldC()"  class="step-button step-button-previous">Old Client</button>
            </div>
            <div class="form-group" v-if="oldClient">
              <label for="Phone">Phone</label>
              <input
                type="text"
                class="form-control"
                placeholder="Enter client phone"
                v-model="SPhone"
              />
              <label for="Password">Password</label>
              <input
                type="password"
                class="form-control"
                placeholder="Enter client Password"
                v-model="Password"
              />
            </div>
            <div class="form-group" v-if="newClient">
              <label for="fullName">Full Name</label>
              <input
                type="text"
                class="form-control"
                placeholder="Enter your name"
                v-model="fullName"
              />
              <label for="Email">Email</label>
              <input
                type="text"
                class="form-control"
                placeholder="Enter email"
                v-model="Email"
              />
              <label for="Address">Address</label>
              <input
                type="text"
                class="form-control"
                placeholder="Enter address"
                v-model="adress"
              />
              <label for="Age">Age</label>
              <input
                type="text"
                class="form-control"
                placeholder="Enter Age"
                v-model="Age"
              />
              <label for="gender">Gender</label>
              <select class="form-control" v-model="gender">
                <option>Man</option>
                <option>Woman</option>
                <option>Gay</option>
              </select>
              <label for="CIN">CIN</label>
              <input
                type="text"
                class="form-control"
                placeholder="Enter CIN"
                v-model="CIN"
              />
            </div>
          </tab-content>
          <tab-content title="About Receiver">
            <div class="form-group">
              <label for="fullName">Full Name</label>
              <input
                type="text"
                class="form-control"
                placeholder="Enter Receiver name"
                v-model="RfullName"
              />
              <label for="Phone">Phone</label>
              <input
                type="text"
                class="form-control"
                placeholder="Receiver Phone"
                v-model="RPhone"
              />
            </div>
          </tab-content>
          <tab-content title="Finishing Up">
            <div class="form-group">

              <label for="Value">Amount</label>
              <input
                type="text"
                class="form-control"
                placeholder="Enter Somme d'argent à envoyer"
                v-model="Value"
              />

              <label for="TFT" >Type Frais Transfert</label>
              <select class="form-control" v-model="TFT">
                <option>CHARGE_BENEFICIAIRE</option>
                <option>CHARGE_DONNEUR_ORDRE</option>
                <option>CHARGE_PARTAGE</option>
              </select>
              <label for="motif_transfert">Motif de Transfert</label>
              <select class="form-control" v-model="MT">
                <option>Soutien_familial</option>
                <option>Epargne</option>
                <option>Cadeau</option>
                <option>Autre</option>
              </select>
              <input type="checkbox" id="checkbox" v-model="isNotified">
              <label for="checkbox" style="margin-left:5px">To Notify</label>
            </div>
          </tab-content>
        </form-wizard>

<!--Form of recieve-->
        <form-wizard @onNextStep="nextTab"  @onComplete="onComplete1"  style="background-color: #27293d;" v-if="Key2">
          <tab-content title="About You Receiver" :selected="true">
            <div class="form-group" v-if="newClient = true">
              <label for="fullName">Phone</label><!--should we change the name ???-->
              <input
                type="text"
                class="form-control"
                placeholder="Enter your phone number"
                v-model="fullName"
              />
             
            </div>
          </tab-content>
          <tab-content title="Reference" >
            <div class="form-group">
              <label for="reference">Reference</label>
              <input
                type="text"
                class="form-control"
                placeholder="Enter transfert reference"
                v-model="TReference"
              />

            </div>
          </tab-content>
          <tab-content title="Finishing Up">
            <div class="form-group">
              <!--Afficher la somme :) -->
              <span>La somme à recevoir : {{RSomme}}</span>
              <!--<label for="Value">Somme</label>
              <input
                type="text"
                class="form-control"
                placeholder="Enter Somme d'argent à envoyer"
                v-model="Value"
              />-->
            </div>
          </tab-content>
        </form-wizard>

<!--Form of recieve-->



      </card>
    </div>
  </div>
</template>

<script>
import { FormWizard, TabContent } from "vue-step-wizard";
import "vue-step-wizard/dist/vue-step-wizard.css";
import TransactionResource from "../api/tx";
  import NotificationTemplate from './Notifications/NotificationTemplate';
  import { BaseAlert } from '@/components';
export default {
  name: "BasicStepperForm",
  components: {
    FormWizard,
    TabContent,
    BaseAlert,
  },
  data() {
    return {
      transactionResource : new TransactionResource(),
      MT:"",
      TFT:"",
      isNotified:false,
      newClient: false,
      oldClient:false,
      ChoixClient:true,
      key1:false,
      Key2:false,
      nextNumber:0,
      KeyO:true,
      fullName: "",
      companyName: "",
      referral: "",
      Email:"",
      Value:"",
      SPhone:"",
      RPhone:"",
      Phone:"",
      RfullName:"",
      CIN:"",
      adress:"",
      Password:"",
      TReference:"",
      RSomme:"900",


    };
  },
  methods: {
    notifyVue(verticalAlign, horizontalAlign) {
        const color = Math.floor(Math.random() * 4 + 1);
        this.$notify({
          component: NotificationTemplate,
          icon: "tim-icons icon-bell-55",
          horizontalAlign: horizontalAlign,
          verticalAlign: verticalAlign,
          type: this.type[color],
          timeout: 0
        });
      },
    async nextTab(){
      this.nextNumber+=1;
      if(this.nextNumber == 2){
        console.log(this.TReference);
        
          const {data} = await this.transactionResource.servirTransferts(this.TReference);
          console.log(data);
          localStorage.setItem('amount',data.montantTransfert);
          this.notifyVue('top', 'center');
          this.$router.push('/app/console');
        }
      },
  async onComplete(){
    // const tx ={

    //     "clientDonneurTele": "+212 613 230339",
    //     "clientBeneficiaireTele": "+212 698 726273",
    //     "agentId":4,
    //     "montantTransfert": 1000.0,
    //     "motifTranfert": "Epargne",
    //     "modeTransfert": "ESPECE",
    //     "typeFraisTransfert": "CHARGE_DONNEUR_ORDRE",
    //     "sourceTransfert": "POINT_DE_VENTE",
    //     "canalTransfert": "POINT_DE_VENTE",
    //     "date": "2022-01-17T11:35:31.000+00:00",
    //     "notified": true
    // }
  const tx = {
        "clientDonneurTele":this.SPhone ,
        "clientBeneficiaireTele": this.RPhone,
        "agentId":localStorage.agentId,
        "montantTransfert": Number(this.Value),
        "motifTranfert": this.MT,
        "modeTransfert": "ESPECE",
        "typeFraisTransfert": this.TFT,
        "sourceTransfert": "POINT_DE_VENTE",
        "canalTransfert": "POINT_DE_VENTE",
        "date": new Date(),
        "notified": this.isNotified
  }
    const {data} = await this.transactionResource.insertTransfert(tx);
  console.log(data);
  },

  async onComplete1(){
    
  const tx = {
        "referenceCode":this.TReference,
        "clientBeneficiaireTele": this.RPhone,
        "agentId":localStorage.agentId,
        "montantTransfert": Number(this.Value),
        "motifTranfert": this.MT,
        "modeTransfert": "ESPECE",
        "typeFraisTransfert": this.TFT,
        "sourceTransfert": "POINT_DE_VENTE",
        "canalTransfert": "POINT_DE_VENTE",
        "date": new Date(),
        "notified": this.isNotified
  }
  
  const {data} = await this.transactionResource.insertTransfert(tx);
  console.log(data);
  },
  
    envoyer(){
      this.key1=true;
      console.log(this.key1);
      this.KeyO=false;
    },
    receive(){
      this.Key2=true;
      this.KeyO=false;
    },
    newC(){
      this.newClient=true;
      this.ChoixClient=false;
    },
    oldC(){
      this.oldClient=true;
      this.ChoixClient=false;
    }
  },
};
</script>

<style>
.step-pills{
background-color: #27293d;
}
.step-progress .bar {
  content: "";
  height: 1rem;
  border-radius: 1rem;
  background-color: #389466;
}
.step-button-previous {
  background-color: #333442;
}
.step-button-next {
  background-color: #389466;
}
.step-pills .step-item.active {

    border: 1px solid #389466;

}
.step-pills .step-item a{
  color: #9b9b9b;
}
.step-pills .step-item{
background-color: #333442;
}
.form-control {
  border-color: #2b3553;
  border-radius: 0.4285rem;
  font-size: 0.75rem;
  background-color: #27293d;
  -webkit-transition: color 0.3s ease-in-out;
  transition: color 0.3s ease-in-out;
}
.form-control option{
  background-color: #27293d;
}
.step-header{
background-color: #27293d;
}
.step-body{
background-color: #27293d;
}
</style>
