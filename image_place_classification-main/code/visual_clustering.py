import networkx as nx
import matplotlib.pyplot as plt
path0="E:/bunProject/8/"

f = open(path0+"pairwise_scores.txt", 'r')
lines = f.readlines()
nodeList=[]
nodeSet=set()
edgeList=[]
for line in lines:
    n1, n2, w = line.strip().split(' ')
    #print(n1, n2, w)
    edgeList.append((n1,n2, w))
f.close()

for e in edgeList:
    nodeSet.add(e[0])

#make graph
g1 = nx.Graph()

#add node
g1.add_nodes_from(nodeSet)

#add edge
g1.add_weighted_edges_from(edgeList)

print(nx.info(g1))

plt.show()

#sub graph
sub_graphs = nx.nx.connected_components(g1)
sub_graphs_List = list(sub_graphs)
#number of sub graphs
n = nx.number_connected_components(g1)

pos = nx.spring_layout(g1)
colorlist = [ 'r', 'g', 'b', 'c', 'm', 'y', 'orange', 'skyblue', 'pink', 'limegreen', 'grey']
photoIDLabel= dict()
colorIndex=0;

for i,subG in enumerate(sub_graphs_List):

    ##print("Subgraph:", subG)
    tempList=list(subG)
    for node in tempList:
        photoIDLabel[node]=i

    if colorIndex>=len(colorlist):
        colorIndex=0
    nx.draw(nx.subgraph(g1, subG), pos=pos, with_labels=True, edge_color = colorlist[colorIndex],
            node_color = colorlist[colorIndex])
    colorIndex+=1

# plt.show()

photoIDFile=dict()
f2 = open(path0+"list.txt", 'r')
lines = f2.readlines()
for i,l in enumerate(lines):
    p=l.strip().split(' ')[0][2:]
    photoIDFile[i] = p
f2.close()

# print()
# print(photoIDFile)
# print()

fileLabel=dict()
for i in range(len(photoIDLabel)):
    photoFileName=photoIDFile.get(i)
    photoClusterLabel=photoIDLabel.get(str(i))
    # print(photoFileName, photoClusterLabel)
    fileLabel[photoFileName]=photoClusterLabel

# for v in fileLabel.items():
#     print(v)

import os
path=path0
for filename in os.listdir(path):
    visual_label=fileLabel.get(filename)
    geo_label=filename[:2]
    # print(geo_label,visual_label)
    new_filename = geo_label+str(visual_label)+'_'+filename[2:]
    os.rename(path+filename, path+new_filename)
print()
print("FINISH")



print("number of clusters in graph: ", nx.number_connected_components(g1))
#for i,subG in enumerate(sub_graphs_List):
 #   print("Subgraph ", i, ":", subG)
plt.title("graph 1")
plt.show()
