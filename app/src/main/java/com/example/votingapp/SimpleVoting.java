package com.example.votingapp;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.7.0.
 */
@SuppressWarnings("rawtypes")
public class SimpleVoting extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5033600060016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060008060006101000a81548160ff0219169083600581111561007057fe5b02179055506121a2806100846000396000f3fe608060405234801561001057600080fd5b506004361061012c5760003560e01c80636c297445116100ad578063e09b8c7911610071578063e09b8c79146105e2578063e81e977b146105ec578063ee74c6781461060a578063f53d0a8e14610614578063f75d64a61461065e5761012c565b80636c297445146104d8578063a1edffa2146104e2578063a3ec138d1461050e578063a7bfab161461057c578063da36cffe146105865761012c565b80632f95355b116100f45780632f95355b1461032e578063378a2178146103e957806337d05ddc146103f357806338db6dd31461047657806341b1b046146104ba5761012c565b80630121b93f14610131578063013cf08b1461015f5780630a2eb3011461020d5780631af8690914610269578063229acb1714610287575b600080fd5b61015d6004803603602081101561014757600080fd5b810190808035906020019092919050505061068a565b005b61018b6004803603602081101561017557600080fd5b8101908080359060200190929190505050610a27565b6040518080602001838152602001828103825284818151815260200191508051906020019080838360005b838110156101d15780820151818401526020810190506101b6565b50505050905090810190601f1680156101fe5780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b61024f6004803603602081101561022357600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610af0565b604051808215151515815260200191505060405180910390f35b610271610b49565b6040518082815260200191505060405180910390f35b6102b36004803603602081101561029d57600080fd5b8101908080359060200190929190505050610bed565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156102f35780820151818401526020810190506102d8565b50505050905090810190601f1680156103205780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6103e76004803603602081101561034457600080fd5b810190808035906020019064010000000081111561036157600080fd5b82018360208201111561037357600080fd5b8035906020019184600183028401116401000000008311171561039557600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050509192919290505050610d29565b005b6103f1610efb565b005b6103fb611158565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561043b578082015181840152602081019050610420565b50505050905090810190601f1680156104685780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6104b86004803603602081101561048c57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611294565b005b6104c26115dd565b6040518082815260200191505060405180910390f35b6104e06115ea565b005b6104ea6117c8565b604051808260058111156104fa57fe5b60ff16815260200191505060405180910390f35b6105506004803603602081101561052457600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506117da565b604051808415151515815260200183151515158152602001828152602001935050505060405180910390f35b61058461181e565b005b6105c86004803603602081101561059c57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506119fc565b604051808215151515815260200191505060405180910390f35b6105ea611a55565b005b6105f4611c33565b6040518082815260200191505060405180910390f35b610612611cb9565b005b61061c611e97565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b610666611ebd565b6040518082600581111561067657fe5b60ff16815260200191505060405180910390f35b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff1661072f576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401808060200182810382526036815260200180611ffa6036913960400191505060405180910390fd5b6003600581111561073c57fe5b6000809054906101000a900460ff16600581111561075657fe5b146107ac576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252603a815260200180612030603a913960400191505060405180910390fd5b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160019054906101000a900460ff161561086f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601c8152602001807f7468652063616c6c65722068617320616c726561647920766f7465640000000081525060200191505060405180910390fd5b6108776115dd565b81106108eb576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807f50726f706f73616c20696e646578206f7574206f6620626f756e64000000000081525060200191505060405180910390fd5b60018060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160016101000a81548160ff02191690831515021790555080600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206001018190555060016002828154811061099b57fe5b9060005260206000209060020201600101600082825401925050819055507f0902475a854b82199b4f5d6b81327ec4c4ec077ce0356a106ddc2748c5285ed33382604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390a150565b60028181548110610a3457fe5b9060005260206000209060020201600091509050806000018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610ae05780601f10610ab557610100808354040283529160200191610ae0565b820191906000526020600020905b815481529060010190602001808311610ac357829003601f168201915b5050505050908060010154905082565b60008060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16149050919050565b6000600580811115610b5757fe5b6000809054906101000a900460ff166005811115610b7157fe5b14610bc7576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252603e815260200180612130603e913960400191505060405180910390fd5b600260035481548110610bd657fe5b906000526020600020906002020160010154905090565b6060610bf76115dd565b8210610c6b576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807f50726f706f73616c20696e646578206f7574206f6620626f756e64000000000081525060200191505060405180910390fd5b60028281548110610c7857fe5b90600052602060002090600202016000018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610d1d5780601f10610cf257610100808354040283529160200191610d1d565b820191906000526020600020905b815481529060010190602001808311610d0057829003601f168201915b50505050509050919050565b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610dcf576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252603581526020018061206a6035913960400191505060405180910390fd5b60016005811115610ddc57fe5b6000809054906101000a900460ff166005811115610df657fe5b14610e4c576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252603e815260200180611f79603e913960400191505060405180910390fd5b600260405180604001604052808381526020016000815250908060018154018082558091505090600182039060005260206000209060020201600090919290919091506000820151816000019080519060200190610eab929190611ed3565b50602082015181600101555050507fdcd2e8cd237900037efa515c36a3caa6812766647c16535dd5fc3c6fd641154c6001600280549050036040518082815260200191505060405180910390a150565b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610fa1576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252603581526020018061206a6035913960400191505060405180910390fd5b60046005811115610fae57fe5b6000809054906101000a900460ff166005811115610fc857fe5b1461101e576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401808060200182810382526043815260200180611fb76043913960600191505060405180910390fd5b6000809050600080905060008090505b60028054905081101561109357826002828154811061104957fe5b9060005260206000209060020201600101541115611086576002818154811061106e57fe5b90600052602060002090600202016001015492508091505b808060010191505061102e565b508060038190555060056000806101000a81548160ff021916908360058111156110b957fe5b02179055507fa1f82a14ea162408785ef8969f7cf08b2bb72b081f8d4e2eda49846e52f439c660405160405180910390a17f9474022b1c97b70d3479aafe8672c447d1837ad99ae1f735eced168a65252d1660046000809054906101000a900460ff166040518083600581111561112c57fe5b60ff16815260200182600581111561114057fe5b60ff1681526020019250505060405180910390a15050565b606060058081111561116657fe5b6000809054906101000a900460ff16600581111561118057fe5b146111d6576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252603e815260200180612130603e913960400191505060405180910390fd5b6002600354815481106111e557fe5b90600052602060002090600202016000018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561128a5780601f1061125f5761010080835404028352916020019161128a565b820191906000526020600020905b81548152906001019060200180831161126d57829003601f168201915b5050505050905090565b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161461133a576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252603581526020018061206a6035913960400191505060405180910390fd5b6000600581111561134757fe5b6000809054906101000a900460ff16600581111561136157fe5b146113b7576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252604a81526020018061209f604a913960600191505060405180910390fd5b600160008273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff161561147a576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f74686520766f74657220697320616c726561647920726567697374657265640081525060200191505060405180910390fd5b60018060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160006101000a81548160ff0219169083151502179055506000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160016101000a81548160ff0219169083151502179055506000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101819055507f42b360c89ba65a59ac7fec024a7bbb2c21d84b5abe5b5178ba0d7e34653a813081604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390a150565b6000600280549050905090565b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614611690576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252603581526020018061206a6035913960400191505060405180910390fd5b6001600581111561169d57fe5b6000809054906101000a900460ff1660058111156116b757fe5b1461170d576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252603e815260200180611f79603e913960400191505060405180910390fd5b60026000806101000a81548160ff0219169083600581111561172b57fe5b02179055507ff629233eb3546085ffcbbb79c947a38b98b14a6915bcc004a0c1a34ae38c29d860405160405180910390a17f9474022b1c97b70d3479aafe8672c447d1837ad99ae1f735eced168a65252d1660016000809054906101000a900460ff166040518083600581111561179e57fe5b60ff1681526020018260058111156117b257fe5b60ff1681526020019250505060405180910390a1565b6000809054906101000a900460ff1681565b60016020528060005260406000206000915090508060000160009054906101000a900460ff16908060000160019054906101000a900460ff16908060010154905083565b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146118c4576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252603581526020018061206a6035913960400191505060405180910390fd5b600360058111156118d157fe5b6000809054906101000a900460ff1660058111156118eb57fe5b14611941576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252603a815260200180612030603a913960400191505060405180910390fd5b60046000806101000a81548160ff0219169083600581111561195f57fe5b02179055507f86a7e3ed2917a2b5f100160b6e558c8e7ae6e4fb8f2673d860d3f11b4f345e7260405160405180910390a17f9474022b1c97b70d3479aafe8672c447d1837ad99ae1f735eced168a65252d1660036000809054906101000a900460ff16604051808360058111156119d257fe5b60ff1681526020018260058111156119e657fe5b60ff1681526020019250505060405180910390a1565b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff169050919050565b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614611afb576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252603581526020018061206a6035913960400191505060405180910390fd5b60006005811115611b0857fe5b6000809054906101000a900460ff166005811115611b2257fe5b14611b78576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252604a81526020018061209f604a913960600191505060405180910390fd5b60016000806101000a81548160ff02191690836005811115611b9657fe5b02179055507f65b51a6e225dbda234ac3ca54831842456809aa60205dd84e758670106b4a6f360405160405180910390a17f9474022b1c97b70d3479aafe8672c447d1837ad99ae1f735eced168a65252d1660008060009054906101000a900460ff1660405180836005811115611c0957fe5b60ff168152602001826005811115611c1d57fe5b60ff1681526020019250505060405180910390a1565b6000600580811115611c4157fe5b6000809054906101000a900460ff166005811115611c5b57fe5b14611cb1576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252603e815260200180612130603e913960400191505060405180910390fd5b600354905090565b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614611d5f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252603581526020018061206a6035913960400191505060405180910390fd5b60026005811115611d6c57fe5b6000809054906101000a900460ff166005811115611d8657fe5b14611ddc576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260478152602001806120e96047913960600191505060405180910390fd5b60036000806101000a81548160ff02191690836005811115611dfa57fe5b02179055507ffca8377aff3a08252b769c58e6769a534922eabaaf9952691ea9cfcaed41fbd860405160405180910390a17f9474022b1c97b70d3479aafe8672c447d1837ad99ae1f735eced168a65252d1660026000809054906101000a900460ff1660405180836005811115611e6d57fe5b60ff168152602001826005811115611e8157fe5b60ff1681526020019250505060405180910390a1565b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60008060009054906101000a900460ff16905090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10611f1457805160ff1916838001178555611f42565b82800160010185558215611f42579182015b82811115611f41578251825591602001919060010190611f26565b5b509050611f4f9190611f53565b5090565b611f7591905b80821115611f71576000816000905550600101611f59565b5090565b9056fe746869732066756e6374696f6e2063616e2062652063616c6c6564206f6e6c7920647572696e672070726f706f73616c7320726567697374726174696f6e746869732066756e6374696f6e2063616e2062652063616c6c6564206f6e6c792061667465722074686520766f74696e672073657373696f6e2068617320656e6465647468652063616c6c6572206f6620746869732066756e6374696f6e206d7573742062652061207265676973746572656420766f746572746869732066756e6374696f6e2063616e2062652063616c6c6564206f6e6c7920647572696e672074686520766f74696e672073657373696f6e7468652063616c6c6572206f6620746869732066756e6374696f6e206d757374206265207468652061646d696e6973747261746f72746869732066756e6374696f6e2063616e2062652063616c6c6564206f6e6c79206265666f72652070726f706f73616c7320726567697374726174696f6e206861732073746172746564746869732066756e6374696f6e2063616e2062652063616c6c6564206f6e6c792061667465722070726f706f73616c7320726567697374726174696f6e2068617320656e646564746869732066756e6374696f6e2063616e2062652063616c6c6564206f6e6c7920616674657220766f7465732068617665206265656e2074616c6c696564a265627a7a723158203e7c9580aaca4a2b1fa1b876f5ab09bc4c5aa0e3e6c00b9b8bd2b7201ceddb2064736f6c63430005100032";

    public static final String FUNC_ADMINISTRATOR = "administrator";

    public static final String FUNC_ENDPROPOSALSREGISTRATION = "endProposalsRegistration";

    public static final String FUNC_ENDVOTINGSESSION = "endVotingSession";

    public static final String FUNC_GETPROPOSALDESCRIPTION = "getProposalDescription";

    public static final String FUNC_GETPROPOSALSNUMBER = "getProposalsNumber";

    public static final String FUNC_GETWINNINGPROPOSALDESCRIPTION = "getWinningProposalDescription";

    public static final String FUNC_GETWINNINGPROPOSALID = "getWinningProposalId";

    public static final String FUNC_GETWINNINGPROPOSALVOTECOUNTS = "getWinningProposalVoteCounts";

    public static final String FUNC_GETWORKFLOWSTATUS = "getWorkflowStatus";

    public static final String FUNC_ISADMINISTRATOR = "isAdministrator";

    public static final String FUNC_ISREGISTEREDVOTER = "isRegisteredVoter";

    public static final String FUNC_PROPOSALS = "proposals";

    public static final String FUNC_REGISTERPROPOSAL = "registerProposal";

    public static final String FUNC_REGISTERVOTER = "registerVoter";

    public static final String FUNC_STARTPROPOSALSREGISTRATION = "startProposalsRegistration";

    public static final String FUNC_STARTVOTINGSESSION = "startVotingSession";

    public static final String FUNC_TALLYVOTES = "tallyVotes";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_VOTERS = "voters";

    public static final String FUNC_WORKFLOWSTATUS = "workflowStatus";

    public static final Event PROPOSALREGISTEREDEVENT_EVENT = new Event("ProposalRegisteredEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event PROPOSALSREGISTRATIONENDEDEVENT_EVENT = new Event("ProposalsRegistrationEndedEvent", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event PROPOSALSREGISTRATIONSTARTEDEVENT_EVENT = new Event("ProposalsRegistrationStartedEvent", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event VOTEDEVENT_EVENT = new Event("VotedEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event VOTERREGISTEREDEVENT_EVENT = new Event("VoterRegisteredEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event VOTESTALLIEDEVENT_EVENT = new Event("VotesTalliedEvent", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event VOTINGSESSIONENDEDEVENT_EVENT = new Event("VotingSessionEndedEvent", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event VOTINGSESSIONSTARTEDEVENT_EVENT = new Event("VotingSessionStartedEvent", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event WORKFLOWSTATUSCHANGEEVENT_EVENT = new Event("WorkflowStatusChangeEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}));
    ;

    @Deprecated
    protected SimpleVoting(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SimpleVoting(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SimpleVoting(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SimpleVoting(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ProposalRegisteredEventEventResponse> getProposalRegisteredEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PROPOSALREGISTEREDEVENT_EVENT, transactionReceipt);
        ArrayList<ProposalRegisteredEventEventResponse> responses = new ArrayList<ProposalRegisteredEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ProposalRegisteredEventEventResponse typedResponse = new ProposalRegisteredEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ProposalRegisteredEventEventResponse> proposalRegisteredEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ProposalRegisteredEventEventResponse>() {
            @Override
            public ProposalRegisteredEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PROPOSALREGISTEREDEVENT_EVENT, log);
                ProposalRegisteredEventEventResponse typedResponse = new ProposalRegisteredEventEventResponse();
                typedResponse.log = log;
                typedResponse.proposalId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ProposalRegisteredEventEventResponse> proposalRegisteredEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PROPOSALREGISTEREDEVENT_EVENT));
        return proposalRegisteredEventEventFlowable(filter);
    }

    public List<ProposalsRegistrationEndedEventEventResponse> getProposalsRegistrationEndedEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PROPOSALSREGISTRATIONENDEDEVENT_EVENT, transactionReceipt);
        ArrayList<ProposalsRegistrationEndedEventEventResponse> responses = new ArrayList<ProposalsRegistrationEndedEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ProposalsRegistrationEndedEventEventResponse typedResponse = new ProposalsRegistrationEndedEventEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ProposalsRegistrationEndedEventEventResponse> proposalsRegistrationEndedEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ProposalsRegistrationEndedEventEventResponse>() {
            @Override
            public ProposalsRegistrationEndedEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PROPOSALSREGISTRATIONENDEDEVENT_EVENT, log);
                ProposalsRegistrationEndedEventEventResponse typedResponse = new ProposalsRegistrationEndedEventEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Flowable<ProposalsRegistrationEndedEventEventResponse> proposalsRegistrationEndedEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PROPOSALSREGISTRATIONENDEDEVENT_EVENT));
        return proposalsRegistrationEndedEventEventFlowable(filter);
    }

    public List<ProposalsRegistrationStartedEventEventResponse> getProposalsRegistrationStartedEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PROPOSALSREGISTRATIONSTARTEDEVENT_EVENT, transactionReceipt);
        ArrayList<ProposalsRegistrationStartedEventEventResponse> responses = new ArrayList<ProposalsRegistrationStartedEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ProposalsRegistrationStartedEventEventResponse typedResponse = new ProposalsRegistrationStartedEventEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ProposalsRegistrationStartedEventEventResponse> proposalsRegistrationStartedEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ProposalsRegistrationStartedEventEventResponse>() {
            @Override
            public ProposalsRegistrationStartedEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PROPOSALSREGISTRATIONSTARTEDEVENT_EVENT, log);
                ProposalsRegistrationStartedEventEventResponse typedResponse = new ProposalsRegistrationStartedEventEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Flowable<ProposalsRegistrationStartedEventEventResponse> proposalsRegistrationStartedEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PROPOSALSREGISTRATIONSTARTEDEVENT_EVENT));
        return proposalsRegistrationStartedEventEventFlowable(filter);
    }

    public List<VotedEventEventResponse> getVotedEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VOTEDEVENT_EVENT, transactionReceipt);
        ArrayList<VotedEventEventResponse> responses = new ArrayList<VotedEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VotedEventEventResponse typedResponse = new VotedEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.voter = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.proposalId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<VotedEventEventResponse> votedEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, VotedEventEventResponse>() {
            @Override
            public VotedEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VOTEDEVENT_EVENT, log);
                VotedEventEventResponse typedResponse = new VotedEventEventResponse();
                typedResponse.log = log;
                typedResponse.voter = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.proposalId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<VotedEventEventResponse> votedEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VOTEDEVENT_EVENT));
        return votedEventEventFlowable(filter);
    }

    public List<VoterRegisteredEventEventResponse> getVoterRegisteredEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VOTERREGISTEREDEVENT_EVENT, transactionReceipt);
        ArrayList<VoterRegisteredEventEventResponse> responses = new ArrayList<VoterRegisteredEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VoterRegisteredEventEventResponse typedResponse = new VoterRegisteredEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.voterAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<VoterRegisteredEventEventResponse> voterRegisteredEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, VoterRegisteredEventEventResponse>() {
            @Override
            public VoterRegisteredEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VOTERREGISTEREDEVENT_EVENT, log);
                VoterRegisteredEventEventResponse typedResponse = new VoterRegisteredEventEventResponse();
                typedResponse.log = log;
                typedResponse.voterAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<VoterRegisteredEventEventResponse> voterRegisteredEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VOTERREGISTEREDEVENT_EVENT));
        return voterRegisteredEventEventFlowable(filter);
    }

    public List<VotesTalliedEventEventResponse> getVotesTalliedEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VOTESTALLIEDEVENT_EVENT, transactionReceipt);
        ArrayList<VotesTalliedEventEventResponse> responses = new ArrayList<VotesTalliedEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VotesTalliedEventEventResponse typedResponse = new VotesTalliedEventEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<VotesTalliedEventEventResponse> votesTalliedEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, VotesTalliedEventEventResponse>() {
            @Override
            public VotesTalliedEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VOTESTALLIEDEVENT_EVENT, log);
                VotesTalliedEventEventResponse typedResponse = new VotesTalliedEventEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Flowable<VotesTalliedEventEventResponse> votesTalliedEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VOTESTALLIEDEVENT_EVENT));
        return votesTalliedEventEventFlowable(filter);
    }

    public List<VotingSessionEndedEventEventResponse> getVotingSessionEndedEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VOTINGSESSIONENDEDEVENT_EVENT, transactionReceipt);
        ArrayList<VotingSessionEndedEventEventResponse> responses = new ArrayList<VotingSessionEndedEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VotingSessionEndedEventEventResponse typedResponse = new VotingSessionEndedEventEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<VotingSessionEndedEventEventResponse> votingSessionEndedEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, VotingSessionEndedEventEventResponse>() {
            @Override
            public VotingSessionEndedEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VOTINGSESSIONENDEDEVENT_EVENT, log);
                VotingSessionEndedEventEventResponse typedResponse = new VotingSessionEndedEventEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Flowable<VotingSessionEndedEventEventResponse> votingSessionEndedEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VOTINGSESSIONENDEDEVENT_EVENT));
        return votingSessionEndedEventEventFlowable(filter);
    }

    public List<VotingSessionStartedEventEventResponse> getVotingSessionStartedEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VOTINGSESSIONSTARTEDEVENT_EVENT, transactionReceipt);
        ArrayList<VotingSessionStartedEventEventResponse> responses = new ArrayList<VotingSessionStartedEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VotingSessionStartedEventEventResponse typedResponse = new VotingSessionStartedEventEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<VotingSessionStartedEventEventResponse> votingSessionStartedEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, VotingSessionStartedEventEventResponse>() {
            @Override
            public VotingSessionStartedEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VOTINGSESSIONSTARTEDEVENT_EVENT, log);
                VotingSessionStartedEventEventResponse typedResponse = new VotingSessionStartedEventEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Flowable<VotingSessionStartedEventEventResponse> votingSessionStartedEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VOTINGSESSIONSTARTEDEVENT_EVENT));
        return votingSessionStartedEventEventFlowable(filter);
    }

    public List<WorkflowStatusChangeEventEventResponse> getWorkflowStatusChangeEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(WORKFLOWSTATUSCHANGEEVENT_EVENT, transactionReceipt);
        ArrayList<WorkflowStatusChangeEventEventResponse> responses = new ArrayList<WorkflowStatusChangeEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            WorkflowStatusChangeEventEventResponse typedResponse = new WorkflowStatusChangeEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousStatus = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.newStatus = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<WorkflowStatusChangeEventEventResponse> workflowStatusChangeEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, WorkflowStatusChangeEventEventResponse>() {
            @Override
            public WorkflowStatusChangeEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(WORKFLOWSTATUSCHANGEEVENT_EVENT, log);
                WorkflowStatusChangeEventEventResponse typedResponse = new WorkflowStatusChangeEventEventResponse();
                typedResponse.log = log;
                typedResponse.previousStatus = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.newStatus = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<WorkflowStatusChangeEventEventResponse> workflowStatusChangeEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(WORKFLOWSTATUSCHANGEEVENT_EVENT));
        return workflowStatusChangeEventEventFlowable(filter);
    }

    public RemoteFunctionCall<String> administrator() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ADMINISTRATOR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> endProposalsRegistration() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ENDPROPOSALSREGISTRATION, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> endVotingSession() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ENDVOTINGSESSION, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getProposalDescription(BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPROPOSALDESCRIPTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getProposalsNumber() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPROPOSALSNUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getWinningProposalDescription() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETWINNINGPROPOSALDESCRIPTION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getWinningProposalId() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETWINNINGPROPOSALID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getWinningProposalVoteCounts() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETWINNINGPROPOSALVOTECOUNTS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getWorkflowStatus() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETWORKFLOWSTATUS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> isAdministrator(String _address) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISADMINISTRATOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _address)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isRegisteredVoter(String _voterAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISREGISTEREDVOTER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _voterAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Tuple2<String, BigInteger>> proposals(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PROPOSALS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple2<String, BigInteger>>(function,
                new Callable<Tuple2<String, BigInteger>>() {
                    @Override
                    public Tuple2<String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> registerProposal(String proposalDescription) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REGISTERPROPOSAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(proposalDescription)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> registerVoter(String _voterAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REGISTERVOTER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _voterAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> startProposalsRegistration() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_STARTPROPOSALSREGISTRATION, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> startVotingSession() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_STARTVOTINGSESSION, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> tallyVotes() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TALLYVOTES, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> vote(BigInteger proposalId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(proposalId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple3<Boolean, Boolean, BigInteger>> voters(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VOTERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<Boolean, Boolean, BigInteger>>(function,
                new Callable<Tuple3<Boolean, Boolean, BigInteger>>() {
                    @Override
                    public Tuple3<Boolean, Boolean, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<Boolean, Boolean, BigInteger>(
                                (Boolean) results.get(0).getValue(), 
                                (Boolean) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> workflowStatus() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_WORKFLOWSTATUS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static SimpleVoting load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleVoting(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SimpleVoting load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleVoting(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SimpleVoting load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SimpleVoting(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SimpleVoting load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SimpleVoting(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SimpleVoting> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SimpleVoting.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<SimpleVoting> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SimpleVoting.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SimpleVoting> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SimpleVoting.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SimpleVoting> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SimpleVoting.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ProposalRegisteredEventEventResponse extends BaseEventResponse {
        public BigInteger proposalId;
    }

    public static class ProposalsRegistrationEndedEventEventResponse extends BaseEventResponse {
    }

    public static class ProposalsRegistrationStartedEventEventResponse extends BaseEventResponse {
    }

    public static class VotedEventEventResponse extends BaseEventResponse {
        public String voter;

        public BigInteger proposalId;
    }

    public static class VoterRegisteredEventEventResponse extends BaseEventResponse {
        public String voterAddress;
    }

    public static class VotesTalliedEventEventResponse extends BaseEventResponse {
    }

    public static class VotingSessionEndedEventEventResponse extends BaseEventResponse {
    }

    public static class VotingSessionStartedEventEventResponse extends BaseEventResponse {
    }

    public static class WorkflowStatusChangeEventEventResponse extends BaseEventResponse {
        public BigInteger previousStatus;

        public BigInteger newStatus;
    }
}
