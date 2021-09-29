def read_data(filename):
    with open(filename, 'r',encoding='utf-8') as f:
        data = [line.split('\t') for line in f.read().splitlines()]
        data = data[1:]
    return data

train_data = read_data('data/nsmc/ratings_train.txt')
test_data = read_data('data/nsmc/ratings_test.txt')

from konlpy.tag import Okt

okt = Okt()

import json

def tokenize(doc):
    return ['/'.join(t) for t in okt.pos(doc, norm=True, stem=True)]

train_docs = [(tokenize(row[1]), row[2]) for row in train_data]
test_docs = [(tokenize(row[1]), row[2]) for row in test_data]

with open('data/nsmc//train_docs.json', 'w', encoding="utf-8") as make_file:
    json.dump(train_docs, make_file, ensure_ascii=False, indent="\t")
with open('data/nsmc/test_docs.json', 'w', encoding="utf-8") as make_file:
    json.dump(test_docs, make_file, ensure_ascii=False, indent="\t")

tokens = [t for d in train_docs for t in d[0]]

import nltk
text = nltk.Text(tokens, name='NMSC')

selected_words = [f[0] for f in text.vocab().most_common(1000)]#10000 줄이면 시간줄음

def term_frequency(doc):
    return [doc.count(word) for word in selected_words]

train_x = [term_frequency(d) for d, _ in train_docs]
test_x = [term_frequency(d) for d, _ in test_docs]
train_y = [c for _, c in train_docs]
test_y = [c for _, c in test_docs]

import numpy as np

x_train = np.asarray(train_x).astype('float32')
x_test = np.asarray(test_x).astype('float32')
y_train = np.asarray(train_y).astype('float32')
y_test = np.asarray(test_y).astype('float32')

from tensorflow.keras import models
from tensorflow.keras import layers
from tensorflow.keras import optimizers
from tensorflow.keras import losses
from tensorflow.keras import metrics

model = models.Sequential()
model.add(layers.Dense(64, activation='relu'))
model.add(layers.Dense(64, activation='relu'))
model.add(layers.Dense(1, activation='sigmoid'))
model.compile(optimizer=optimizers.RMSprop(lr=0.001),
             loss=losses.binary_crossentropy,
             metrics=[metrics.binary_accuracy])

def predict_pos_neg(review):

    token = tokenize(review)
    tf = term_frequency(token)
    data = np.expand_dims(np.asarray(tf).astype('float32'), axis=0)
    score = float(model.predict(data))
    if(score > 0.5):
        print("[{}]는 {:.2f}이므로 긍정 리뷰 예상합니다.(0:부정   1.0:긍정)\n".format(review, score))
    else:
        print("[{}]는 {:.2f}이므로 부정 리뷰 예상합니다.(0:부정   1.0:긍정)\n".format(review, score))
    return score

f2 = open("data/sentinalAnalsisResult.txt", 'w',encoding='utf-8')
f2.write("부정:<0.5    중립:0.5    긍정: >0.5")
f2.write("\n\n\n")

file = open('data/movieReview.txt','r',encoding='utf-8')
lines = file.readlines()
for line in lines:
        f2.write(line)
        f2.write(str(predict_pos_neg(line)))
        f2.write("\n")