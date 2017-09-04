package cadprodutos.cursoapp.com.cadprodutos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

import cadprodutos.cursoapp.com.cadprodutos.BDHelper.ProdutosBd;
import cadprodutos.cursoapp.com.cadprodutos.model.Produtos;

public class FormularioProdutos extends AppCompatActivity{
    EditText editText_NomeProd, editText_Descricao, editText_Quantidade;
    Button btn_Poliform;
    Produtos editarProduto, produto;
    ProdutosBd bdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_produtos);

        produto = new Produtos();
        bdHelper = new ProdutosBd(FormularioProdutos.this);

        Intent intent = getIntent();
        editarProduto = (Produtos) intent.getSerializableExtra("produto-escolhido");

        editText_NomeProd = (EditText) findViewById(R.id.editText_NomeProd);
        editText_Descricao = (EditText) findViewById(R.id.editText_Descricao);
        editText_Quantidade =(EditText) findViewById(R.id.editText_Quantidade);

        btn_Poliform = (Button) findViewById(R.id.btn_Poliform);

        if (editarProduto !=null){
            btn_Poliform.setText("Modificar Produto");

            editText_NomeProd.setText(editarProduto.getNomeProduto());
            editText_Descricao.setText(editarProduto.getDescricao());
            editText_Quantidade.setText(editarProduto.getQuantidade()+"");

            produto.setId(editarProduto.getId());


        }else{
            btn_Poliform.setText("Cadastrar Novo Produto");
        }

        btn_Poliform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                produto.setNomeProduto(editText_NomeProd.getText().toString());
                produto.setDescricao(editText_Descricao.getText().toString());
                produto.setQuantidade(Integer.parseInt(editText_Quantidade.getText().toString()));

                if(btn_Poliform.getText().toString().equals("Cadastrar Novo Produto")){
                    bdHelper.salvarProduto(produto);
                    bdHelper.close();
                }else{
                    bdHelper.alterarProduto(produto);
                    bdHelper.close();
                }
            }
        });

    }

}
