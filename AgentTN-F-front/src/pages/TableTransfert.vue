<template>
    <div class="row">
      <div class="col-12">
        <card :title="table1.title">
          <div class="table-responsive">
            <base-table :data="tableData"
                        :columns="table1.columns"
                        thead-classes="text-primary">
            </base-table>
          </div>
        </card>
      </div>
    </div>
</template>
<script>
import { BaseTable } from "@/components";
import TransactionResource from "../api/tx"
const tableColumns = ["referenceCode", "transfertId", "canalTransfert", "clientBeneficiaireTele" ,"clientDonneurTele","date","etatTransfert","notified","montantTransfert","motifTranfert"];



export default {
  components: {
    BaseTable
  },
  data() {
    return {
      transactionResource:new TransactionResource(),
      tableData : [],
      table1: {
        title: "Transfert Table",
        columns: [...tableColumns],
      },
    };
  },
  mounted(){
    this.getTx();
  },
  methods:{
    async getTx(){
      const {data} = await this.transactionResource.getTransferts();
      console.log(data);
      data.forEach(element => {
        const tx1 = {
          "referencecode":element.referenceCode,
          "transfertid":element.transfertId,
          "canaltransfert":element.canalTransfert,
          "clientbeneficiairetele":element.clientBeneficiaireTele ,
          "clientdonneurtele":element.clientDonneurTele,
          "date":element.date,
          "etattransfert":element.etatTransfert,
          "notified":element.notified,
          "montanttransfert":element.montantTransfert,
          "motiftranfert":element.motifTranfert
        }
        this.tableData.push(tx1);
      });

    }
  }
};
</script>
<style>
</style>
