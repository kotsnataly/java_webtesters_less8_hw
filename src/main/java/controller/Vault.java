package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import model.Transaction;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Vault implements Xeon {

    float totalVaultAmount = 1_000_000f;
    List<Transaction> transactionList = new ArrayList<>();


    public Vault() throws IOException {

    }

    //логика отправки пользователем денег, с проверками на сумму баланса пользователя и сумму самого хранилища.
    public boolean makeTransaction(User userFrom, User userTo, float amount) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter objectWriter = mapper.writerFor(Transaction.class);
        Transaction t = new Transaction();
        if (amount > this.totalVaultAmount) {
            //total vault sum is less than amount
            t.setSuccess(false);
            t.setReason("Amount is too large for the vault");
            t.setAmount(amount);
            transactionList.add(t);
            return false;

        } else if (userFrom.getUserAccount().getTotalSum() < amount) {
            //total sum is less than transaction sum
            t.setSuccess(false);
            t.setReason("This amount is inaccessible for the user");
            t.setAmount(amount);
            transactionList.add(t);
            return false;
        } else {
            userFrom.getUserAccount().setTotalSum(-amount);
            userTo.getUserAccount().setTotalSum(amount);
            t.setSuccess(true);
            t.setReason("Transaction done");
            t.setAmount(amount);
            transactionList.add(t);
            return true;
        }
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}